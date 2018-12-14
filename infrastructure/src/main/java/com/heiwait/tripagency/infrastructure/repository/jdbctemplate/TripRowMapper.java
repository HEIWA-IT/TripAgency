package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripRowMapper implements RowMapper<Trip> {
    @Override
    public Trip mapRow(ResultSet row, int i) throws SQLException {
        Destination destination = new Destination();
        destination.setName(row.getString("destination"));

        Trip trip = new Trip();
        trip.setDestination(destination);
        trip.setAgencyFees(row.getInt("agency_fees"));
        trip.setTravelFees(row.getInt("travel_fees"));
        trip.setTicketPrice(row.getInt("ticket_price"));
        return trip;
    }
}
