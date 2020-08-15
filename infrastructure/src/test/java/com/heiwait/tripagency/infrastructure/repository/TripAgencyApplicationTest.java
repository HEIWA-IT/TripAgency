package com.heiwait.tripagency.infrastructure.repository;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.domain.error.BusinessException;
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
public class TripAgencyApplicationTest {

    @Autowired
    @Qualifier("TripRepositoryJpaAdapter")
    private TripRepositoryPort tripRepositoryPort;

    @Test
    public void findTripByDestination_with_a_valid_destination_should_find_a_trip() {
        Destination paris = new Destination();
        paris.setName("Paris");

        Trip parisTrip = tripRepositoryPort.findTripByDestination(paris);

        Trip expectedTrip = new Trip(paris, 300, 50, 200);
        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTrip.agencyFees());
        assertThat(parisTrip.travelFees()).isEqualTo(expectedTrip.travelFees());
    }

    @Test(expected = BusinessException.class)
    public void findTripByDestination_with_a_invalid_destination_should_return_a_destination_not_found_message() {
        Destination pari = new Destination();
        pari.setName("Pari");

        Trip pariTrip = tripRepositoryPort.findTripByDestination(pari);
    }
}