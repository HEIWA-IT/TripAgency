package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;
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
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Trip findTripByDestination(Destination destination) {
        String sql = "SELECT destination, agency_fees, travel_fees FROM trip WHERE destination = ?";
        RowMapper<Trip> rowMapper = new TripRowMapper();
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, destination.getName());
        } catch (EmptyResultDataAccessException e) {
            throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }
    }
}
