package com.javaacademy.cryptowallet.service;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Profile("prod")
public class ExchangeService {
    private static final String JSON_PATH = "$.rates.USD";
    private final OkHttpClient client;
    @Value("${api.cbr.base-url}")
    private String baseUrl;

    public BigDecimal convertRubToUsd(BigDecimal amountRub) throws IOException {
        BigDecimal rateDollar = getRateDollar();
        return amountRub.divide(rateDollar, 2, RoundingMode.HALF_DOWN);
    }

    public BigDecimal convertUsdToRub(BigDecimal amountUsd) throws IOException {
        BigDecimal rateDollar = getRateDollar();
        return amountUsd.multiply(rateDollar);
    }

    private BigDecimal getRateDollar() throws IOException {
        String responseBody = getAllData();
        Double rateDollar = JsonPath.parse(responseBody).read(JSON_PATH, Double.class);

        return BigDecimal.valueOf(rateDollar);
    }

    private String getAllData() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(baseUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null && response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RuntimeException();
             }
        }

    }
}