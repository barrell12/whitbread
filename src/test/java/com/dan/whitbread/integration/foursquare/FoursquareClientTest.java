package com.dan.whitbread.integration.foursquare;

import com.dan.whitbread.integration.foursquare.domain.FoursquareResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "foursquare.url=localhost:9090")
@AutoConfigureWireMock(port = 9090)
public class FoursquareClientTest {

    @Autowired
    private FoursquareClient foursquareClient;

    @Value("classpath:response.json")
    private Resource response;

    private String responseString;

    @Before
    public void before() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
        br.close();
        responseString = stringBuilder.toString();
    }

    @Test
    public void clientWorksCorrectly() throws Exception {
        stubFor(get(urlEqualTo("/v2/venues/search?v=v&client_id=foo&client_secret=bar&near=near"))
                .willReturn(aResponse()
                        .withBody(responseString)
                        .withHeader("Content-type", "application/json")));

        FoursquareResponse actual = foursquareClient.searchVenues("v", "foo", "bar", "near");
        assertEquals("Trafalgar Square", actual.getResponse().getVenues().get(0).getName());
    }
}