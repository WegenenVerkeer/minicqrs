package be.wegenenverkeer.minicqrs.demoapp;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

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
}
