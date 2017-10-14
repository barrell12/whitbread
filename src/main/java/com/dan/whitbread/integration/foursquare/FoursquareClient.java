package com.dan.whitbread.integration.foursquare;

import com.dan.whitbread.integration.foursquare.domain.FoursquareResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "foursquare-client", url = "${foursquare.url}")
public interface FoursquareClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/venues/search")
    FoursquareResponse searchVenues(@RequestParam("v") String version,
                                    @RequestParam("client_id") String clientId,
                                    @RequestParam("client_secret") String clientSecret,
                                    @RequestParam("near") String near);

}
