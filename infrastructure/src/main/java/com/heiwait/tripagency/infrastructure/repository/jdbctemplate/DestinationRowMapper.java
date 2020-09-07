package com.heiwait.tripagency.infrastructure.repository.jdbctemplate;

import com.heiwait.tripagency.domain.Destination;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationRowMapper implements RowMapper<Destination> {
    @Override
    public Destination mapRow(ResultSet row, int i) throws SQLException {
        String dest = row.getString("name");
        return new Destination(dest);
    }
}