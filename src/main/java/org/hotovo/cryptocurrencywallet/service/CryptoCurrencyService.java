package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.dto.PageableDto;

import java.util.List;

public interface CryptoCurrencyService {
    List<CryptoCurrency> getAllCryptoCurrencies(PageableDto dto);

}
