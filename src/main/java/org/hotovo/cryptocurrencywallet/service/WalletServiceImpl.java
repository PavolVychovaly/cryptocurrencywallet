package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.dao.WalletDao;
import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;
import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.model.dto.WalletDto;
import org.hotovo.cryptocurrencywallet.model.enumerator.CURRENCY_ENUM;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    @Override
    public Wallet findById(Long id) {
        return walletDao.findById(id);
    }

    @Override
    public List<Wallet> findAll() {
        return walletDao.findAll();
    }

    @Override
    public Wallet create(WalletDto walletDto) {
        Wallet wallet = new Wallet();
        BeanUtils.copyProperties(walletDto, wallet);

        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setSymbol(walletDto.getCryptoCurrencySymbol());

        List<Price> prices = new ArrayList<>();
        Price price = new Price();
        price.setValue(walletDto.getAmount());
        price.setCode(CURRENCY_ENUM.USD.name());
        prices.add(price);

        cryptoCurrency.setPrices(prices);
        wallet.setCryptoCurrency(cryptoCurrency);

        return walletDao.create(wallet);
    }

    @Override
    public Wallet update(WalletDto walletDto) {
        // load wallet from static Map from Dao layer
        Wallet wallet = findById(walletDto.getId());
        BeanUtils.copyProperties(walletDto, wallet);

        CryptoCurrency cryptoCurrency = wallet.getCryptoCurrency();
        cryptoCurrency.setSymbol(walletDto.getCryptoCurrencySymbol());

        List<Price> prices = cryptoCurrency.getPrices();
        for (Price price : prices) {
            price.setValue(walletDto.getAmount());
        }

        return wallet;
    }

    @Override
    public void delete(Long id) {
        walletDao.delete(id);
    }

    @Override
    public void transferValuesBetweenTwoWallets() {
        walletDao.transferValuesBetweenTwoWallets();
    }

    @Override
    public Wallet buyCurrency() {
        return walletDao.buyCurrency();
    }

}
