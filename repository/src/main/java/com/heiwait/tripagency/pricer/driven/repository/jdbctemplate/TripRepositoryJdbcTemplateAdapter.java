package com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository.jdbctemplate;

import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.Destination;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.Trip;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("TripRepositoryJdbcTemplateAdapter")
public class TripRepositoryJdbcTemplateAdapter implements TripRepositoryPort {

    private final JdbcTemplate jdbcTemplate;

    public TripRepositoryJdbcTemplateAdapter(JdbcTemplate jdbcTemplate) {
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