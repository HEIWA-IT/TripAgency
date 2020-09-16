package com.heiwait.tripagency.infrastructure.repository;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.infrastructure.repository.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
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

    @Test
    public void findTripByDestinationMock_with_a_valid_destination_should_find_a_trip() {
        final Destination paris = new Destination("Paris");

        Trip parisTrip = tripRepositoryMockPort.findTripByDestination(paris);

        Trip expectedTrip = new Trip(50, 300, 200);
        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTrip.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTrip.ticketPrice());
    }

    @Test
    public void findTripByDestinationSpringData_with_a_valid_destination_should_find_a_trip() {
        final Destination paris = new Destination("Paris");

        Trip parisTrip = tripRepositoryJpaPort.findTripByDestination(paris);

        Trip expectedTrip = new Trip(50, 300, 200);
        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTrip.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTrip.ticketPrice());
    }


    @Test
    public void findTripByDestinationJdbcTemplate_with_a_valid_destination_should_find_a_trip() {
        final Destination paris = new Destination("Paris");

        Trip parisTrip = tripRepositoryJdbcTemplatePort.findTripByDestination(paris);

        Trip expectedTrip = new Trip(50, 300, 200);
        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTrip.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTrip.ticketPrice());
    }

    @Test
    public void findTripByDestinationJdbcTemplate_with_a_invalid_destination_should_return_a_missing_destination() {
        final Destination pari = new Destination("Pari");

        Trip pariTrip = tripRepositoryJdbcTemplatePort.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.MISSING_DESTINATION);
    }

    @Test
    public void findTripByDestination_with_a_invalid_destination_should_return_a_missing_destination() {
        final Destination pari = new Destination("Pari");

        Trip pariTrip = tripRepositoryJpaPort.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.MISSING_DESTINATION);
    }
}