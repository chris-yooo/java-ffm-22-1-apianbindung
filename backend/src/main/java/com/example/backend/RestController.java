package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {

private final RestService restService;

    @GetMapping("/stockPriceApple")

    public RestOut getPriceFromFinnhub() {
        return restService.getPriceFromFinnhub();
    }

    @Scheduled(cron = "* */5 * * * *")
    public void resetCache5min() {
        restService.resetCache();
        restService.getPriceFromFinnhub();
    }
}
