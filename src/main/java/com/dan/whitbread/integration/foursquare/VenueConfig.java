package com.dan.whitbread.integration.foursquare;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VenueConfig {

    @Value("${foursquare.url}")
    private String foursquareUrl;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${foursquare.version}")
    private String version;

    @Bean
    public FoursquareVenueSearcher getVenueSearcher(FoursquareClient foursquareClient) {
        return near -> foursquareClient.searchVenues(version, clientId, clientSecret, near);
    }
}
