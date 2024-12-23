package com.javaacademy.cryptowallet.service.exchange;

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
public class ExchangeService implements RubToUsdConverter {
    private static final String RESPONSE_MESSAGE_EXCEPTION = "Ошибка запроса: %s - %s.";
    private static final String JSON_PATH = "$.rates.USD";
    public static final int ROUNDING = 4;
    private final OkHttpClient client;
    @Value("${api.cbr.base-url}")
    private String baseUrl;

    @Override
    public BigDecimal convertRubToUsd(BigDecimal amountRub) {
        BigDecimal rateDollar = getRateDollar();
        return amountRub.multiply(rateDollar);
    }

    @Override
    public BigDecimal convertUsdToRub(BigDecimal amountUsd) {
        BigDecimal rateDollar = getRateDollar();
        return amountUsd.divide(rateDollar, ROUNDING, RoundingMode.HALF_DOWN);
    }

    private BigDecimal getRateDollar() {
        try {
            String responseBody = getAllData();
            Double rateDollar = JsonPath.parse(responseBody).read(JSON_PATH, Double.class);

            return BigDecimal.valueOf(rateDollar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
                throw new RuntimeException(RESPONSE_MESSAGE_EXCEPTION.formatted(response.code(), response.message()));
            }
        }

    }
}
