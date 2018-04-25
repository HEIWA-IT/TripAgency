package com.heiwait.tripagency.infrastructure.repository.ti;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.infrastructure.repository.config.AppConfig;
import com.heiwait.tripagency.infrastructure.repository.liquibase.LiquibaseHelper;
import liquibase.Liquibase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TravelFeesRepoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TripRepositoryPort tripRepositoryPort;

    private Optional<Liquibase> liquibase;

    @Before
    public void _setUp() {
        liquibase = LiquibaseHelper.loadData(dataSource,
              "db/changelog/db.changelog-master.yaml");
    }

   @After
    public void _tearDown()
    {
        LiquibaseHelper.rollbackAndClose(liquibase);
    }

    @Test
    public void test_findTripByDestination_with_a_valid_destination_should_find_a_trip() {
        Destination paris = new Destination();
        paris.setName("Paris");

        Trip parisTrip = tripRepositoryPort.findTripByDestination(paris);

        Trip expectedTrip = new Trip(paris, 300, 50);
        assertThat(parisTrip.agencyFees()).isEqualTo(expectedTrip.agencyFees());
        assertThat(parisTrip.travelFees()).isEqualTo(expectedTrip.travelFees());
    }
}
