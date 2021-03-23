package com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository;

import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.Destination;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.Trip;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.TripRepositoryPort;
import com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository.config.AppConfig;
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
    private TripRepositoryPort tripRepositoryJpa;

    @Autowired
    @Qualifier("TripRepositoryJdbcTemplateAdapter")
    private TripRepositoryPort tripRepositoryJdbcTemplate;

    @Autowired
    @Qualifier("TripRepositoryMockAdapter")
    private TripRepositoryPort tripRepositoryMock;


    final Destination pari = new Destination("Pari");
    final Destination paris = new Destination("Paris");
    final Trip expectedTripForParis = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(100);
        builder.setStayFees(800);
        builder.setAgencyFees(50);
    }).build();

    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_the_mock_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryMock.findTripByDestination(paris);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_mock_adapter_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryMock.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }

    /**********************************************************************************************************************/
    @Test
    public void findTripByDestination_should_find_a_trip_with_the_jpa_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryJpa.findTripByDestination(paris);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_jpa_adapter_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryJpa.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }

    /**********************************************************************************************************************/
    @Test
    public void should_find_a_trip_with_the_jdbc_adapter_and_a_valid_destination() {
        Trip parisTrip = tripRepositoryJdbcTemplate.findTripByDestination(paris);

        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTripForParis.agencyFees());
        assertThat(parisTrip.ticketPrice()).isEqualTo(expectedTripForParis.ticketPrice());
    }

    @Test
    public void should_return_a_missing_destination_with_the_jdbc_adapter_and_a_missing_destination() {
        Trip pariTrip = tripRepositoryJdbcTemplate.findTripByDestination(pari);
        assertThat(pariTrip).isEqualTo(Trip.Builder.MISSING_DESTINATION);
    }
}