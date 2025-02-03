package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.PaymentResponseDto;
import com.doit_well.trip_service.entity.PaymentRequest;
import com.doit_well.trip_service.entity.Reservationstatus;
import com.doit_well.trip_service.entity.customer.CreateCustomerRequest;
import com.doit_well.trip_service.entity.customer.CustomerDto;
import com.doit_well.trip_service.entity.reservation.ChargeRequest;
import com.doit_well.trip_service.entity.reservation.Reservation;
import com.doit_well.trip_service.entity.reservation.ReservationDto;
import com.doit_well.trip_service.entity.reservation.ReservationRequest;
import com.doit_well.trip_service.entity.trip.Trip;
import com.doit_well.trip_service.exception.ReservationException;
import com.doit_well.trip_service.exception.TripException;
import com.doit_well.trip_service.mapper.ReservationMapper;
import com.doit_well.trip_service.repository.ReservationRepository;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.utils.StringGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;


@Service
public class ReservationService implements IReservationService {

    @Autowired
    private ITripService tripService;
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper mapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private WebClient webClient;
    @Autowired
    private StringGenerator stringGenerator;

    @Value("${customer.client.baseUrl:http://customer-service}")
    String customerClientBaseUrl;
    @Value("${payment.client.baseUrl:http://payment-service}")
    String paymentClientBaseUrl;

    @Override
    public ReservationDto processReservation(ReservationRequest request) throws JsonProcessingException {

        if (request == null) throw new ReservationException("Reservation is null");

        Trip trip = tripService.findTripById(request.tripId());

        if (trip.getSeats() < 1) throw new TripException("No more seats available for this trip");

        CreateCustomerRequest customerRequest = reservationMapper.toCustomerRequest(request);
        ChargeRequest chargeRequest = reservationMapper.toChargeRequest(request);

        if (customerRequest == null) throw new ReservationException("No customer details for this reservation");

        if (chargeRequest == null) throw new ReservationException("No payment details for this reservation");

        PaymentResponseDto paymentResponseDto = processPayment(chargeRequest);

        Reservation reservation;

        switch (paymentResponseDto.status()) {
            case COMPLETED -> {
                CustomerDto customerDto = createCustomerApi(customerRequest);
                tripService.updateSeats(trip.getId(), request.numberOfPlaces());

                reservation = new Reservation(
                        null,
                        stringGenerator.generateReference(),
                        LocalDateTime.now(ZoneId.of("Europe/Paris")),
                        Reservationstatus.COMPLETED,
                        trip,
                        customerDto.id(),
                        null,
                        paymentResponseDto.stripeEmail()
                );

                return reservationMapper.toDto(repository.save(reservation));
            }
            case CONFIRMED -> {
                CustomerDto customerDto = createCustomerApi(customerRequest);
                tripService.updateSeats(trip.getId(), request.numberOfPlaces());

                reservation = new Reservation(
                        null,
                        stringGenerator.generateReference(),
                        LocalDateTime.now(ZoneId.of("Europe/Paris")),
                        Reservationstatus.CONFIRMED,
                        trip,
                        customerDto.id(),
                        null,
                        paymentResponseDto.stripeEmail()
                );

                return reservationMapper.toDto(repository.save(reservation));
            }
            default -> {
                reservation = new Reservation(
                        null,
                        null,
                        LocalDateTime.now(ZoneId.of("Europe/Paris")),
                        paymentResponseDto.status(),
                        trip,
                        null,
                        null,
                        paymentResponseDto.stripeEmail()
                );
                return reservationMapper.toDto(reservation);
            }
        }
    }

    private PaymentResponseDto processPayment(ChargeRequest request) {
        return Objects.requireNonNull(webClient
                .post()
                .uri(paymentClientBaseUrl + "/api/v1/booking/payment")
                .bodyValue(BodyInserters.fromValue(request))
                .retrieve()
                .toEntity(PaymentResponseDto.class).block()).getBody();
    }

    public CustomerDto createCustomerApi(CreateCustomerRequest request) {
        return Objects.requireNonNull(webClient
                .post()
                .uri(customerClientBaseUrl + "/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .toEntity(CustomerDto.class).block()).getBody();
    }

    public void paymentWarehousing(CreateCustomerRequest request, PaymentResponseDto paymentResponseDto, Trip trip) {
        webClient
                .post()
                .uri(paymentClientBaseUrl + "/api/v1/booking/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .toEntity(CustomerDto.class).subscribe(
                        responseEntity -> {
                            CustomerDto customerDto = responseEntity.getBody();

                            // TODO: Payment Warehousing
                        },
                        error -> {
                            if (error instanceof WebClientResponseException) {
                                WebClientResponseException ex = (WebClientResponseException) error;
                                HttpStatusCode status = ex.getStatusCode();
                                throw new ReservationException("Error Status Code: " + status.value());
                            } else {
                                throw new ReservationException("An unexpected error occurred: " + error.getMessage());
                            }
                        }
                );
    }

    private void processPaymentByKafka(Trip trip, ChargeRequest request) throws JsonProcessingException {
        PaymentRequest paymentRequest = new PaymentRequest(
                trip.getId(),
                "",
                (double) request.getAmount(),
                request.getCurrency(),
                request.getStripeEmail(),
                request.getStripeToken()
        );
        kafkaTemplate.send(AppConstants.BUS_LOCATION, mapper.writeValueAsString(paymentRequest));
    }
}
