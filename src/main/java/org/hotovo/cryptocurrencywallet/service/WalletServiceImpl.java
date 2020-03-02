package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.common.CryptoCurrencyMapUtil;
import org.hotovo.cryptocurrencywallet.dao.CryptoCurrencyDao;
import org.hotovo.cryptocurrencywallet.dao.WalletDao;
import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;
import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.model.dto.BuyCurrencyDto;
import org.hotovo.cryptocurrencywallet.model.dto.TransferWalletDto;
import org.hotovo.cryptocurrencywallet.model.dto.WalletDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CryptoCurrencyDao cryptoCurrencyDao;

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
        setCurrencyAndPrices(walletDto.getCurrencySymbol(), wallet.getAmount(), cryptoCurrency);

        wallet.setCryptoCurrency(cryptoCurrency);

        return walletDao.create(wallet);
    }

    @Override
    public Wallet update(WalletDto walletDto) {
        // load wallet from static Map from Dao layer
        Wallet wallet = findById(walletDto.getId());
        BeanUtils.copyProperties(walletDto, wallet);

        CryptoCurrency cryptoCurrency = wallet.getCryptoCurrency();
        setCurrencyAndPrices(walletDto.getCurrencySymbol(), wallet.getAmount(), cryptoCurrency);

        return wallet;
    }

    private void setCurrencyAndPrices(String cryptoCurrencySymbol, BigDecimal amount, CryptoCurrency cryptoCurrency) {
        cryptoCurrency.setSymbol(cryptoCurrencySymbol);
        cryptoCurrency.setName(CryptoCurrencyMapUtil.getCurrencyName(cryptoCurrencySymbol));

        List<Price> prices = cryptoCurrencyDao.getPricesOfCryptocurrencyInOtherCurrencies(cryptoCurrencySymbol);
        for (Price price : prices) {
            price.setValue(price.getValue().multiply(amount));
        }
        cryptoCurrency.setPrices(prices);
    }

    @Override
    public void delete(Long id) {
        walletDao.delete(id);
    }

    @Override
    public void transferValuesBetweenTwoWallets(TransferWalletDto dto) {
        setWalletValuesAfterBuying(dto.getFromWalletId(), dto.getAmount(), dto.getCurrencyOfAmount(), true);
        setWalletValuesAfterBuying(dto.getToWalletId(), dto.getAmount(), dto.getCurrencyOfAmount(), false);
    }

    @Override
    public Wallet buyCurrency(BuyCurrencyDto dto) {
        return setWalletValuesAfterBuying(dto.getWalletId(), dto.getAmount(), dto.getCurrencyOfAmount(), true);
    }

    private Wallet setWalletValuesAfterBuying(Long walletId, BigDecimal amount, String currencyOfAmount, boolean substract) {
        Wallet wallet = findById(walletId);

        Price price = cryptoCurrencyDao.getPriceOfCryptocurrencyInOtherCurrency(wallet.getCryptoCurrency().getSymbol(), currencyOfAmount);
        BigDecimal finalAmount = amount.divide(price.getValue(), 2, RoundingMode.HALF_UP);

        if (substract) {
            wallet.setAmount(wallet.getAmount().subtract(finalAmount));
        } else {
            wallet.setAmount(wallet.getAmount().add(finalAmount));
        }

        List<Price> prices = cryptoCurrencyDao.getPricesOfCryptocurrencyInOtherCurrencies(wallet.getCryptoCurrency().getSymbol());
        for (Price price1 : prices) {
            price1.setValue(price1.getValue().multiply(wallet.getAmount()));
        }

        wallet.getCryptoCurrency().setPrices(prices);

        return wallet;
    }

}
