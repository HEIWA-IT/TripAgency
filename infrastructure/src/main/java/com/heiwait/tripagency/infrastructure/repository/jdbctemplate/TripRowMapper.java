package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripRowMapper implements RowMapper<Trip> {
    @Override
    public Trip mapRow(ResultSet row, int i) throws SQLException {
        int agencyFees = row.getInt("agency_fees");
        int stayFees = row.getInt("stay_fees");
        int ticketPrice = row.getInt("ticket_price");

        return new TripBuilder().withAgencyFees(agencyFees).withStayFees(stayFees).withTicketPrice(ticketPrice).build();
    }
}