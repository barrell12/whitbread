package com.dan.whitbread.controllers;

import com.dan.whitbread.integration.foursquare.domain.Venue;
import com.dan.whitbread.services.VenueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VenueController.class)
public class VenueControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VenueService mockVenueService;

    @Test
    public void rejectsUnsupportedAcceptType() throws Exception {
        mvc.perform(get("/venues/foo")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void rejectsUnsupportedHTTPMethod() throws Exception {
        mvc.perform(
                post("/venues/foo"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void returnsValidJSONOutput() throws Exception {
        Venue venue = new Venue();
        venue.setName("foo");

        String expected = "[{\"name\":\"foo\"}]";

        given(mockVenueService.getNearbyVenues(Matchers.any())).willReturn(Collections.singletonList(venue));

        mvc.perform(get("/venues/foo")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }
}