package be.wegenenverkeer.minicqrs.core.projection;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.wegenenverkeer.minicqrs.core.projection.AbstractProjection.ProjectionId;

@Configuration
public class ProjectionConfiguration {

  @Bean
  public Cache<ProjectionId, Long> getProjectionOffsetCache(CacheManager cacheManager) {
    return cacheManager
        .createCache(ProjectionId.class.getCanonicalName(),
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(ProjectionId.class, Long.class,
                    ResourcePoolsBuilder.heap(100))
                .build());
  }
}
