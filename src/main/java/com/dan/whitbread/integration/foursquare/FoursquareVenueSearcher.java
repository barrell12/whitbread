package com.dan.whitbread.integration.foursquare;

import com.dan.whitbread.integration.foursquare.domain.FoursquareResponse;

public interface FoursquareVenueSearcher {
    FoursquareResponse searchVenues(String near);
}
