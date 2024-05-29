package be.wegenenverkeer.minicqrs.parent;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.r2dbc.postgresql.codec.Json;

import java.util.Arrays;

@Configuration
@EnableConfigurationProperties({ R2dbcProperties.class, FlywayProperties.class })
class DataJdbcConfiguration {
  private static Logger LOG = LoggerFactory.getLogger(DataJdbcConfiguration.class);
  private ObjectMapper objectMapper;

  DataJdbcConfiguration(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @WritingConverter
  class EntityWritingConverter implements Converter<JsonNode, Json> {
    @Override
    public Json convert(@SuppressWarnings("null") JsonNode source) {
      try {
        return Json.of(objectMapper.writeValueAsString(source));
      } catch (JsonProcessingException e) {
        LOG.error("Cannot write JSON to postgres", e);
        return Json.of("");
      }
    }
  }

  @ReadingConverter
  class EntityReadingConverter implements Converter<Json, JsonNode> {
    @Override
    public JsonNode convert(@SuppressWarnings("null") Json source) {
      try {
        return objectMapper.readTree(source.asString());
      } catch (JsonProcessingException e) {
        LOG.error("Cannot read JSON from postgres", e);
        return objectMapper.createObjectNode();
      }
    }
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
  public R2dbcCustomConversions jdbcCustomConversions() {
    return R2dbcCustomConversions.of(PostgresDialect.INSTANCE,
        Arrays.asList(new EntityReadingConverter(), new EntityWritingConverter()));
  }

}