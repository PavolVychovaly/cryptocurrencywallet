package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;
import org.hotovo.cryptocurrencywallet.model.enumerator.CRYPTO_CURRENCY_ENUM;
import org.hotovo.cryptocurrencywallet.model.enumerator.CURRENCY_ENUM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CryptoCurrencyDaoImpl implements CryptoCurrencyDao {

    @Value("${cryptocompare.api.url}")
    private String cryptocompareApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(cryptocompareApiUrl)
                .queryParam("fsyms", Arrays.stream(CRYPTO_CURRENCY_ENUM.values())
                        .map(t -> t.getValue()).collect(Collectors.joining(",")))
                .queryParam("tsyms", Stream.of(CURRENCY_ENUM.values())
                        .map(Enum::name).collect(Collectors.toList()));

        Map<String, Map<String, Float>> mapResult = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<Map<String, Map<String, Float>>>() {
                }).getBody();

        return mapResultToCryptoCurrency(mapResult);
    }

    private List<CryptoCurrency> mapResultToCryptoCurrency(Map<String, Map<String, Float>> cryptoCurrencyMap) {
        List<CryptoCurrency> result = new ArrayList<>();
        if (!cryptoCurrencyMap.isEmpty()) {
            for (String cryptoCurrency : cryptoCurrencyMap.keySet()) {
                CryptoCurrency cryptoCurrencyObj = new CryptoCurrency();
                cryptoCurrencyObj.setCode(cryptoCurrency);

                List<Price> prices = new ArrayList<>();
                Map<String, Float> currencyMap = cryptoCurrencyMap.get(cryptoCurrency);
                for (String currency : currencyMap.keySet()) {
                    Price price = new Price();
                    price.setCode(currency);
                    price.setValue(currencyMap.get(currency));
                    prices.add(price);
                }
                cryptoCurrencyObj.setPrices(prices);
                result.add(cryptoCurrencyObj);
            }
        }

        return result;
    }
}
