package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.dao.CryptoCurrencyDao;
import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    @Autowired
    private CryptoCurrencyDao cryptoCurrencyDao;

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies() {
        return cryptoCurrencyDao.getAllCryptoCurrencies();
    }
}
