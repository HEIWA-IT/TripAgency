package com.heiwait.tripagency.driven.repository;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.driven.repository.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class TripAgencyApplicationTest {

    @Autowired
    @Qualifier("TripRepositoryJpaAdapter")
    private TripRepositoryPort tripRepositoryPort;

    @Test
    public void findTripByDestination_with_a_valid_destination_should_find_a_trip() {
        final Destination paris = new Destination("Paris");

        Trip parisTrip = tripRepositoryPort.findTripByDestination(paris);

        final Trip expectedTrip = new Trip.Builder().with(builder -> {
            builder.setAgencyFees(50);
            builder.setStayFees(300);
            builder.setTicketPrice(200);
        }).build();
        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTrip.agencyFees());
        assertThat(parisTrip.stayFees()).isEqualTo(expectedTrip.stayFees());
    }

    @Test
    public void findTripByDestination_with_a_invalid_destination_should_return_a_destination_not_found_message() {
        final Destination pari = new Destination("Pari");

        Trip pariTrip = tripRepositoryPort.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
}