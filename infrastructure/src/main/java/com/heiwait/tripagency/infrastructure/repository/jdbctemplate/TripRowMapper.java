package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripRowMapper implements RowMapper<Trip> {
    @Override
    public Trip mapRow(ResultSet row, int i) throws SQLException {
        String dest = row.getString("destination");
        int agencyFees = row.getInt("agency_fees");
        int stayFees = row.getInt("stay_fees");
        int ticketPrice = row.getInt("ticket_price");

        Destination destination = new Destination(dest);
        return new Trip(destination, agencyFees, stayFees, ticketPrice);
    }
}