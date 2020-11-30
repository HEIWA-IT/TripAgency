package com.heiwait.tripagency.driven.repository;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.driven.repository.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class TripRepositoryTest {

    @Autowired
    @Qualifier("TripRepositoryJpaAdapter")
    private TripRepositoryPort tripRepositoryJpaPort;

    @Autowired
    @Qualifier("TripRepositoryJdbcTemplateAdapter")
    private TripRepositoryPort tripRepositoryJdbcTemplatePort;

    @Autowired
    @Qualifier("TripRepositoryMockAdapter")
    private TripRepositoryPort tripRepositoryMockPort;


    final Destination pari = new Destination("Pari");
    final Destination paris = new Destination("Paris");
    final Trip expectedTripForParis = new Trip.Builder().with(builder -> {
        builder.setAgencyFees(50);
        builder.setStayFees(300);
        builder.setTicketPrice(200);
    }).build();

    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_the_mock_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryMockPort.findTripByDestination(paris);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_mock_adapter_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryMockPort.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
    /**********************************************************************************************************************/
    @Test
    public void findTripByDestination_should_find_a_trip_with_the_jpa_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryJpaPort.findTripByDestination(paris);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_jpa_adapter_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryJpaPort.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_the_jdbc_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryJdbcTemplatePort.findTripByDestination(paris);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_jdbc_adapter_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryJdbcTemplatePort.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
}