package com.dan.whitbread.services;

import com.dan.whitbread.integration.foursquare.FoursquareVenueSearcher;
import com.dan.whitbread.integration.foursquare.domain.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VenueService {
    private FoursquareVenueSearcher foursquareVenueSearcher;

    @Autowired
    public VenueService(FoursquareVenueSearcher foursquareVenueSearcher) {
        this.foursquareVenueSearcher = Objects.requireNonNull(foursquareVenueSearcher);
    }

    public List<Venue> getNearbyVenues(String near) {
        return foursquareVenueSearcher.searchVenues(near).getResponse().getVenues();
    }
}
