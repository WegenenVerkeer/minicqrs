package be.wegenenverkeer.minicqrs.parent;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import be.wegenenverkeer.minicqrs.core.projection.ProjectionId;

@SpringBootApplication(scanBasePackages = "be.wegenenverkeer.minicqrs")
@EnableWebFlux
@EnableR2dbcRepositories("be.wegenenverkeer.minicqrs")
public class ParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

	@Bean
	public CacheManager getCacheManager() {
		return CacheManagerBuilder.newCacheManagerBuilder().build(true);
	}

	@Bean
	public Cache<ProjectionId, Long> getProjectionOffsetCache() {
		return getCacheManager()
				.createCache(ProjectionId.class.getCanonicalName(),
						CacheConfigurationBuilder
								.newCacheConfigurationBuilder(ProjectionId.class, Long.class,
										ResourcePoolsBuilder.heap(100)) // TODO: configure
								.build());
	}
}
