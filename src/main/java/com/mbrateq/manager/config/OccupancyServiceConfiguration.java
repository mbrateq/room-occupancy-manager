package com.mbrateq.manager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OccupancyServiceConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "room-occupancy-manager")
  public OccupancyServiceProperties getOccupancyServiceProperties() {
    return new OccupancyServiceProperties();
  }
}
