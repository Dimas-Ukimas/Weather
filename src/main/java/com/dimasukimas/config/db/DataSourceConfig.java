package com.dimasukimas.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.jpa.db.driver}")
    String dbDriver;

    @Value("${DB_URL}")
    String dbUrl;

    @Value("${DB_USER}")
    String dbUser;

    @Value("${DB_PASSWORD}")
    String dbPassword;

    @Bean
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setUsername(dbUser);
        hikariConfig.setPassword(dbPassword);
        hikariConfig.setDriverClassName(dbDriver);

        return new HikariDataSource(hikariConfig);
    }

}
