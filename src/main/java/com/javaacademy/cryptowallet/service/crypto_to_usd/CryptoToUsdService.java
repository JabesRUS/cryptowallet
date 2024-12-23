package com.javaacademy.cryptowallet.service.crypto_to_usd;

import com.javaacademy.cryptowallet.config.ApiProperty;
import com.javaacademy.cryptowallet.entity.CryptoCurrency;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@EnableConfigurationProperties(value = ApiProperty.class)
@RequiredArgsConstructor
@Profile("prod")
public class CryptoToUsdService implements UsdRateProvider {

    private static final String RESPONSE_PATTERN = "%s/simple/price?ids=%s&vs_currencies=usd";
    private static final String RESPONSE_MESSAGE_EXCEPTION = "Ошибка запроса:";
    private static final String PATH_PATTERN = "$.%s.usd";

    private final OkHttpClient client;
    private final ApiProperty apiProperty;

    @Override
    public BigDecimal getRateUsd(CryptoCurrency currency) {
        String currencyFullName = currency.getName();

        try (Response response = executeRequest(currencyFullName)) {
            if (response.isSuccessful() || response.body() != null) {
                String responseBody = response.body().string();
                return extractRateUsd(responseBody, currencyFullName);
            } else {
                throw new RuntimeException(response.code() + "-" + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(RESPONSE_MESSAGE_EXCEPTION, e);
        }
    }

    private BigDecimal extractRateUsd(String responseBody, String currencyFullName) {
        String jsonPath = PATH_PATTERN.formatted(currencyFullName);

        return JsonPath.parse(responseBody).read(JsonPath.compile(jsonPath), BigDecimal.class);
    }

    private Response executeRequest(String currencyFullName) throws IOException {
        String baseUrl = apiProperty.getBaseUrl();
        String ulrRequest = RESPONSE_PATTERN.formatted(baseUrl, currencyFullName);
        Request request = new Request.Builder()
                .get()
                .url(ulrRequest)
                .addHeader(apiProperty.getNameHeader(), apiProperty.getValueHeader())
                .build();
        return client.newCall(request).execute();
    }

}
