package com.dan.whitbread.controllers;

import com.dan.whitbread.integration.foursquare.domain.Venue;
import com.dan.whitbread.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
public class VenueController {
    private VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = Objects.requireNonNull(venueService);
    }

    @GetMapping(value = "/venues/{near}", produces = "application/json")
    @ResponseBody
    public List<Venue> getNearbyVenues(@PathVariable String near) {
        return venueService.getNearbyVenues(near);
    }
}
