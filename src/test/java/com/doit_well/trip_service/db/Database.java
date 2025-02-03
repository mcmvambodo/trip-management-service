package com.doit_well.trip_service.db;

import com.doit_well.trip_service.entity.agency.AgencyDto;
import com.doit_well.trip_service.entity.agency.CreateAgencyRequest;
import com.doit_well.trip_service.entity.car.CarDto;
import com.doit_well.trip_service.entity.car.CarType;
import com.doit_well.trip_service.entity.car.CreateCarRequest;
import com.doit_well.trip_service.entity.company.CompanyDto;
import com.doit_well.trip_service.entity.company.CreateCompanyRequest;
import com.doit_well.trip_service.entity.driver.CreateDriverRequest;
import com.doit_well.trip_service.entity.driver.DriverDto;
import com.doit_well.trip_service.entity.trip.CreateTripRequest;
import com.doit_well.trip_service.entity.trip.TripDto;

import java.time.LocalDateTime;
import java.time.Month;

public class Database {

    public static final CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest(
            "Buca Voyage",
            "Travel all over Cameroon at cheap prices. ",
            "/",
            "Cameroon",
            "Yaounde",
            "Nsimalen",
            "10",
            13615
    );
    public static final CompanyDto companyDto = new CompanyDto(
            1,
            "Buca Voyage",
            "Travel all over Cameroon at cheap prices. ",
            "/",
            "Cameroon",
            "Yaounde",
            "Nsimalen",
            "10",
            13615
    );
    public static final CreateAgencyRequest createAgency = new CreateAgencyRequest(
            "BUC_MVA1",
            "Yaounde-South",
            "All trips from Yaounde to South Cameroon region",
            "/",
                    "Cameroon",
                    "Yaounde",
                    "Nsimalen",
                    "10",
                    13615,
            1
    );

    public static final AgencyDto agencyDto = new AgencyDto(
            1,
            "BUC_MVA1",
            "Aeroport International de Yaounde Nsimalen",
            "All trips from and to Cameroon",
            "/",
            "Cameroon",
            "Yaounde",
            "Nsimalen",
            "10",
            13615,
            "Buca Voyage"
    );

    public static final CreateCarRequest createCar= new CreateCarRequest(
            "Toyota",
            "Coaster",
            CarType.MINIBUS,
            70,
            "CE 0786 LT",
            140000,
            2010,
            "BUC_MVA1"
    );

    public static final CarDto carDto = new CarDto(
            1,
            "Toyota",
            "Coaster",
            CarType.MINIBUS,
            70,
            "CE 0786 LT",
            140000,
            2010,
            "BUC_MVA1"
    );
    public static final CreateDriverRequest createDriverRequest = new CreateDriverRequest(
            "Romeo",
            "Bessala",
            "b.romeo@email.com",
            "+237699574563",
            "Cameroon",
            "Ebolowa",
            "Ebolowwa Si 2",
            "25",
            13245,
            "2018-987675-56342",
            "2020-234-11",
            "Buca Voyages"
    );

    public static final DriverDto driverDto = new DriverDto(
            1,
            "Romeo",
            "Bessala",
            "b.romeo@email.com",
            "+237699574563",
            "Cameroon",
            "Ebolowa",
            "Ebolowwa Si 2",
            "25",
            13245,
            "2018-987675-56342",
            "2020-234-11",
            "Buca Voyages"
    );

    public static final CreateTripRequest createTripRequest = new CreateTripRequest(
            "BUC_MVA1",
            "BUC_EBOL",
            "2025-01-30T12:00:00",
            2500.00,
            "FLEX,LIGHT",
            "Mengong,Mbalmayo",
            70,
            1,
            1,
            1
    );

    public static final TripDto tripDto = new TripDto(
            1,
            "Yaounde",
            "Ebolowa",
            LocalDateTime.of(2025, Month.JANUARY,30,12,0),
            2500.00,
            "Buca Voyage",
            70,
            "FLEX,LIGHT",
            "Mbalmayo,Mengong",
            "Yaounde-South",
            "CE 0786 LT"
    );
}
