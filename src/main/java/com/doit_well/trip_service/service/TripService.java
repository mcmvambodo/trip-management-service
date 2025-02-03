package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.agency.Agency;
import com.doit_well.trip_service.entity.car.Car;
import com.doit_well.trip_service.entity.driver.Driver;
import com.doit_well.trip_service.entity.trip.CreateTripRequest;
import com.doit_well.trip_service.entity.trip.Trip;
import com.doit_well.trip_service.entity.trip.TripDto;
import com.doit_well.trip_service.exception.TripException;
import com.doit_well.trip_service.mapper.TripMapper;
import com.doit_well.trip_service.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class TripService implements ITripService {

    private IAgencyService agencyService;
    private ICarService carService;
    private IDriverService driverService;
    private TripRepository repository;
    private TripMapper mapper;

    @Override
    public List<TripDto> searchTrip(String departure, String destination, String departureDate) {

        List<Trip> trips = repository.findByStopsLikeAndStopsLikeAndDateOfTripGreaterThanEqual(
                departure.toUpperCase(),
                destination.toUpperCase(),
                LocalDateTime.parse(departureDate)
        );
        if (trips == null || trips.size() < 1) {
            return List.of();
        }

        return filterTripByDirection(trips, departure, destination).stream().map(mapper::toDto).toList();
    }

    // We filter the trips that are in the correct direction by finding departure before destination in the stops
    public List<Trip> filterTripByDirection(List<Trip> trips, String departure, String destination) {
        List<Trip> filteredTrips = new ArrayList<>();
        for (Trip trip : trips) {
            String[] stops = trip.getStops().split(",");
            if (Arrays.stream(stops).anyMatch(departure::contains) && Arrays.stream(stops).anyMatch(destination::contains)) {
                for (String stop : stops) {
                    if (stop.equalsIgnoreCase(departure)) {
                        filteredTrips.add(trip);
                        break;
                    } else if (stop.equalsIgnoreCase(destination)) {
                        break;
                    }
                }
            }

        }
        return filteredTrips;
    }

    @Override
    public TripDto createTrip(CreateTripRequest request) {
        if (request == null) throw new TripException("Trip is null");

        // TODO: Calculate the rest of seat base on reservations

        Car car = carService.findCarById(request.carId());
        Agency agency = agencyService.findAgencyById(request.agencyId());
        Driver driver = driverService.findDriverById(request.driverId());

        Trip trip = mapper.toTrip(request);
        trip.setDeparture(request.departure());
        trip.setDestination(request.destination());
        trip.setCar(car);
        trip.setAgency(agency);
        trip.setDriver(driver);
        trip.setStops(request.stops());

        repository.save(trip);
        return mapper.toDto(trip);
    }

    @Override
    public Trip findTripById(Integer tripId) {
        return repository.findById(tripId).orElseThrow(() -> new TripException(String.format("No trip with id %s", tripId)));
    }

    @Override
    public void updateSeats(Integer tripId, Integer bookedPlaces) {
        Trip trip = findTripById(tripId);
        trip.calculateSeats(bookedPlaces);
        repository.save(trip);
    }
}
