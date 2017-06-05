package com.vasyl.medetskyy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by Laden on 03.06.2017.
 */
public class ExchangeRateClient {
    private String url = "http://api.fixer.io/latest";
    private RestTemplate restTemplate;

    public ExchangeRateClient() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Double> getRates(String from, List<String> to) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("base", from)
                .queryParam("symbols", to);

        return restTemplate.getForObject(builder.toUriString(), FixerRate.class).getRates();
    }
}