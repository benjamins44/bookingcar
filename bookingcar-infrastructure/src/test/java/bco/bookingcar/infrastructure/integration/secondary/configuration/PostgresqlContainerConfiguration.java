package bco.bookingcar.infrastructure.integration.secondary.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class PostgresqlContainerConfiguration {
    private static final String IMAGE_VERSION = "postgres:12.7-alpine";

    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>(IMAGE_VERSION)
            .withDatabaseName("docker")
            .withUsername("docker")
            .withPassword("docker")
            .withNetwork(Network.newNetwork())
            .withNetworkAliases("BookingCarNetwork");

    static {
        postgreSQLContainer.start();

        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
    }
}
