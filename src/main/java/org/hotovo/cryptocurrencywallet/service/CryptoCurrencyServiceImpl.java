package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.dao.CryptoCurrencyDao;
import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.dto.PageableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    @Autowired
    private CryptoCurrencyDao cryptoCurrencyDao;

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies(PageableDto dto) {
        return cryptoCurrencyDao.getAllCryptoCurrencies(dto.getPage(), dto.getSize());
    }
}
