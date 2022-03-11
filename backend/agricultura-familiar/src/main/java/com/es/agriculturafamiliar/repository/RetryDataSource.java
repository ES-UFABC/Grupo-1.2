package com.es.agriculturafamiliar.repository;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RetryDataSource extends AbstractDataSource{
    private final DataSource dataSource;
    
    @Override
    @Retryable(maxAttempts = 5, backoff = @Backoff(maxDelay = 5000, multiplier = 1.5))
    public Connection getConnection() throws SQLException {        
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String arg0, String arg1) throws SQLException {
        return dataSource.getConnection(arg0, arg1);
    }
}
