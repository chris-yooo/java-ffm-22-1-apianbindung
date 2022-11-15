package com.example.backend;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class RestService {
    WebClient webclient = WebClient.create();

    static final String CACHE_KEY = "CACHE_KEY";

    @Cacheable(CACHE_KEY)

    public RestOut getPriceFromFinnhub() {
        System.out.println("Methode wurde aufgerufen");
        ResponseEntity<Rest> rest = webclient.get()
                .uri("https://finnhub.io/api/v1/quote?symbol=AAPL&token=cdpnvraad3ia8s05f5egcdpnvraad3ia8s05f5f0")
                .retrieve()
                .toEntity(Rest.class)
                .block();

        if (rest != null) {
            return new RestOut(Objects.requireNonNull(rest.getBody()).c());
        }
        else throw new NullPointerException();
    }

    @CacheEvict(CACHE_KEY)
    public void resetCache() {
    }

    @Scheduled(fixedDelay = 2000)
    public void getPriceFromFinnhubDelay() {
        getPriceFromFinnhub();
    }
}
