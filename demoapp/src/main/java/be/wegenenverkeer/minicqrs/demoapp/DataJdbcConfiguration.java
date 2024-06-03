package be.wegenenverkeer.minicqrs.demoapp;

import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableConfigurationProperties({ R2dbcProperties.class, FlywayProperties.class })
class DataJdbcConfiguration {
  private ConnectionFactory connectionFactory;

  DataJdbcConfiguration(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Bean(initMethod = "migrate", name = "flyway")
  public Flyway flyway(FlywayProperties flywayProperties, R2dbcProperties r2dbcProperties) {
    return Flyway.configure()
        .dataSource(
            flywayProperties.getUrl(),
            r2dbcProperties.getUsername(),
            r2dbcProperties.getPassword())
        .locations(flywayProperties.getLocations()
            .stream()
            .toArray(String[]::new))
        .baselineOnMigrate(true)
        .load();
  }

  @Bean
  @DependsOn("flyway") // Make sure flyway is done before we start anybeans that use the repos.
  public DSLContext jooqDSLContext() {
    return DSL.using(connectionFactory).dsl();
  }

}