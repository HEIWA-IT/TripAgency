package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("TripRepositoryJdbcTemplateAdapter")
public class TripRepositoryJdbcTemplateAdapter implements TripRepositoryPort {

    private JdbcTemplate jdbcTemplate;

    public TripRepositoryJdbcTemplateAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Trip findTripByDestination(Destination destination) {
        String sql = "SELECT destination, agency_fees, stay_fees, ticket_price FROM trip WHERE LOWER(destination) = LOWER(?)";
        RowMapper<Trip> rowMapper = new TripRowMapper();
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, destination.getName());
        } catch (EmptyResultDataAccessException e) {
            return Trip.MISSING_DESTINATION;
        }
    }
}