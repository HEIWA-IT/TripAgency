package com.heiwait.tripagency.pricer.driven.repository.jdbctemplate;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.driven.repository.TripRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("TripRepositoryJdbcTemplate")
public class TripRepositoryJdbcTemplate implements TripRepository {

    private final JdbcTemplate jdbcTemplate;

    public TripRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Trip findTripByDestination(Destination destination) {
        String sql = "SELECT destination, agency_fees, stay_fees, ticket_price FROM trip WHERE LOWER(destination) = LOWER(?)";
        RowMapper<Trip> rowMapper = new TripRowMapper();
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, destination.name());
        } catch (EmptyResultDataAccessException e) {
            return Trip.Builder.MISSING_DESTINATION;
        }
    }
}