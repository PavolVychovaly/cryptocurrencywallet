package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;

import java.util.List;

public interface CryptoCurrencyService {
    List<CryptoCurrency> getAllCryptoCurrencies();

}
