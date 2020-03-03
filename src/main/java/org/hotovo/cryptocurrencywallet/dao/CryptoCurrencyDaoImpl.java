package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.common.CryptoCurrencyMapUtil;
import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CryptoCurrencyDaoImpl implements CryptoCurrencyDao {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cryptocurrency.api.url}")
    private String cryptocurrencyApiUrl;

    @Value("${cryptocurrencymulti.api.url}")
    private String cryptocurrencymultiApiUrl;

    @Value("${price.list}")
    private List<String> priceList;

    @Value("${cryptoCurrencySymbol.list}")
    private List<String> cryptoCurrencySymbolList;

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(cryptocurrencymultiApiUrl)
                .queryParam("fsyms", String.join(",", cryptoCurrencySymbolList))
                .queryParam("tsyms", String.join(",", priceList));

        List<CryptoCurrency> result = new ArrayList<>();
        try {
            Map<String, Map<String, BigDecimal>> mapResult = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Map<String, Map<String, BigDecimal>>>() {
                    }).getBody();

            result = mapResultToCryptoCurrency(mapResult);
        } catch (RestClientException e) {

        }

        return result;
    }

    @Override
    public List<Price> getPricesOfCryptocurrencyInOtherCurrencies(String cryptoCurrencySymbol) {
        return getPricesForCryptoCurrency(cryptoCurrencySymbol, String.join(",", priceList));
    }

    @Override
    public Price getPriceOfCryptocurrencyInOtherCurrency(String currencyFirst, String currencySecond) {
        List<Price> prices = getPricesForCryptoCurrency(currencyFirst, currencySecond);

        return prices.get(0);
    }

    public List<Price> getPricesForCryptoCurrency(String cryptoCurrencySymbol, String currencyCodeList) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(cryptocurrencyApiUrl)
                .queryParam("fsym", cryptoCurrencySymbol)
                .queryParam("tsyms", currencyCodeList);

        List<Price> result = new ArrayList<>();
        try {
            Map<String, BigDecimal> priceMap = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Map<String, BigDecimal>>() {
                    }).getBody();

            result = getPrices(priceMap);
        } catch (RestClientException e) {

        }

        return result;
    }

    private List<CryptoCurrency> mapResultToCryptoCurrency(Map<String, Map<String, BigDecimal>> cryptoCurrencyMap) {
        List<CryptoCurrency> result = new ArrayList<>();
        if (!cryptoCurrencyMap.isEmpty()) {
            for (String cryptoCurrencySymbol : cryptoCurrencyMap.keySet()) {
                Map<String, BigDecimal> priceMap = cryptoCurrencyMap.get(cryptoCurrencySymbol);
                // we want only crypto currencies, which contains prices
                if (!priceMap.isEmpty()) {
                    CryptoCurrency cryptoCurrencyObj = new CryptoCurrency();
                    cryptoCurrencyObj.setSymbol(cryptoCurrencySymbol);
                    cryptoCurrencyObj.setName(CryptoCurrencyMapUtil.getCurrencyName(cryptoCurrencySymbol));
                    cryptoCurrencyObj.setPrices(getPrices(priceMap));
                    result.add(cryptoCurrencyObj);
                }
            }
        }

        return result;
    }

    private List<Price> getPrices(Map<String, BigDecimal> priceMap) {
        List<Price> prices = new ArrayList<>();
        for (String currency : priceMap.keySet()) {
            Price price = new Price();
            price.setCode(currency);
            price.setValue(priceMap.get(currency));
            prices.add(price);
        }

        return prices;
    }
}
