package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Trip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripRowMapper implements RowMapper<Trip> {
    @Override
    public Trip mapRow(ResultSet row, int i) throws SQLException {
        Integer agencyFees = row.getInt("agency_fees");
        Integer stayFees = row.getInt("stay_fees");
        Integer ticketPrice = row.getInt("ticket_price");

        return new Trip.Builder().with(builder -> {
            builder.agencyFees = agencyFees;
            builder.stayFees = stayFees;
            builder.ticketPrice = ticketPrice;
        }).build();
    }
}