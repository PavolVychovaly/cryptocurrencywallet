package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CryptoCurrencyDaoImpl implements CryptoCurrencyDao {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cryptocurrency.api.url}")
    private String cryptocurrencyApiUrl;

    @Value("#{'${currencycode.list}'.split(',')}")
    private List<String> currencyCodeList;

    @Value("#{'${cryptocurrencycode.list}'.split(',')}")
    private List<String> cryptoCurrencyCodeList;

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(cryptocurrencyApiUrl)
                .queryParam("fsyms", cryptoCurrencyCodeList)
                .queryParam("tsyms", currencyCodeList);

        Map<String, Map<String, Float>> mapResult = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<Map<String, Map<String, Float>>>() {
                }).getBody();

        return mapResultToCryptoCurrency(mapResult);
    }

    private List<CryptoCurrency> mapResultToCryptoCurrency(Map<String, Map<String, Float>> cryptoCurrencyMap) {
        List<CryptoCurrency> result = new ArrayList<>();
        if (!cryptoCurrencyMap.isEmpty()) {
            for (String cryptoCurrencySymbol : cryptoCurrencyMap.keySet()) {
                Map<String, Float> currencyMap = cryptoCurrencyMap.get(cryptoCurrencySymbol);
                // we want only crypto currencies, which contains prices
                if (!currencyMap.isEmpty()) {
                    CryptoCurrency cryptoCurrencyObj = new CryptoCurrency();
                    cryptoCurrencyObj.setSymbol(cryptoCurrencySymbol);

                    List<Price> prices = new ArrayList<>();
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
        }

        return result;
    }
}
