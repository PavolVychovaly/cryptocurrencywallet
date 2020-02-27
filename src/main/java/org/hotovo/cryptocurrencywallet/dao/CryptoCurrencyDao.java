package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;

import java.util.List;

public interface CryptoCurrencyDao {

    List<CryptoCurrency> getAllCryptoCurrencies();
}
