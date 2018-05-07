package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Destination;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationRowMapper implements RowMapper<Destination> {
    @Override
    public Destination mapRow(ResultSet row, int i) throws SQLException {
        Destination destination = new Destination();
        destination.setName(row.getString("name"));

        return destination;
    }
}
