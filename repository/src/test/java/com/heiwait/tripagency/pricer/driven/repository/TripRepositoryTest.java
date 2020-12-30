package com.heiwait.tripagency.pricer.driven.repository;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.driven.repository.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TripRepositoryTest {
    @Autowired
    private TripRepositoryHandler tripRepositoryHandler;


    final Destination pari = new Destination("Pari");
    final Destination paris = new Destination("Paris");
    final Trip expectedTripForParis = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(100);
        builder.setStayFees(800);
        builder.setAgencyFees(50);
    }).build();

    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_the_mock_repository_type_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryHandler.findTripByDestination(paris, TripRepositoryType.MOCK);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_mock_repository_type_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryHandler.findTripByDestination(pari, TripRepositoryType.MOCK);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
    /**********************************************************************************************************************/
    @Test
    public void findTripByDestination_should_find_a_trip_with_the_jpa_repository_type_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryHandler.findTripByDestination(paris, TripRepositoryType.JPA);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_jpa_repository_type_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryHandler.findTripByDestination(pari, TripRepositoryType.JPA);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_the_jdbc_repository_type_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryHandler.findTripByDestination(paris, TripRepositoryType.JDBC);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_jdbc_repository_type_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryHandler.findTripByDestination(pari, TripRepositoryType.JDBC);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_no_repository_type_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryHandler.findTripByDestination(paris, TripRepositoryType.UNDEFINED);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_no_repository_type_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryHandler.findTripByDestination(pari, TripRepositoryType.UNDEFINED);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
}