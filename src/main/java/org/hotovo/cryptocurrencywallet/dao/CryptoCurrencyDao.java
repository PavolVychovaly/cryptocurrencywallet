package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;

import java.util.List;

public interface CryptoCurrencyDao {

    List<CryptoCurrency> getAllCryptoCurrencies(Integer page, Integer pageSize);

    List<Price> getPricesOfCryptocurrencyInOtherCurrencies(String cryptoCurrencySymbol);

    Price getPriceOfCryptocurrencyInOtherCurrency(String currencyFirst, String currencySecond);
}
