package org.hotovo.cryptocurrencywallet.view;

import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.dto.PageableDto;
import org.hotovo.cryptocurrencywallet.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cryptocurrency")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @GetMapping
    public List<CryptoCurrency> getAllCryptoCurrencies(PageableDto dto) {
        return cryptoCurrencyService.getAllCryptoCurrencies(dto);
    }
}
