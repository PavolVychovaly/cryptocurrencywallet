package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.common.CryptoCurrencyMapUtil;
import org.hotovo.cryptocurrencywallet.common.exception.BuyingCurrencyException;
import org.hotovo.cryptocurrencywallet.dao.CryptoCurrencyDao;
import org.hotovo.cryptocurrencywallet.dao.WalletDao;
import org.hotovo.cryptocurrencywallet.model.CryptoCurrency;
import org.hotovo.cryptocurrencywallet.model.Price;
import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.model.dto.BuyCurrencyDto;
import org.hotovo.cryptocurrencywallet.model.dto.TransferWalletDto;
import org.hotovo.cryptocurrencywallet.model.dto.WalletDto;
import org.hotovo.cryptocurrencywallet.model.dto.WalletUpdateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
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
    public Wallet update(Long id, WalletUpdateDto dto) {
        Wallet wallet = findById(id);

        if (dto.getName() != null) {
            wallet.setName(dto.getName());
        }

        if (dto.getAmount() != null) {
            wallet.setAmount(dto.getAmount());
        }

        if (dto.getAmount() != null || dto.getCurrencySymbol() != null) {
            String currency = dto.getCurrencySymbol() != null ? dto.getCurrencySymbol() : wallet.getCryptoCurrency().getSymbol();
            CryptoCurrency cryptoCurrency = wallet.getCryptoCurrency();
            setCurrencyAndPrices(currency, wallet.getAmount(), cryptoCurrency);
        }

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
        String walletCurrencySymbol = wallet.getCryptoCurrency().getSymbol();
        BigDecimal finalAmount = BigDecimal.ZERO;

        // in case of the cryptocurrency symbols are equal, we don't must call remote API service
        if (substract && walletCurrencySymbol.equals(currencyOfAmount)) {
            finalAmount = amount;
        } else {
            Price price = cryptoCurrencyDao.getPriceOfCryptocurrencyInOtherCurrency(walletCurrencySymbol, currencyOfAmount);
            if (price != null) {
                finalAmount = amount.divide(price.getValue(), 2, RoundingMode.HALF_UP);
            }
        }

        // we change wallet properties in case of if finalAmount is not equal 0
        if (finalAmount.compareTo(BigDecimal.ZERO) != 0) {
            if (substract) {
                BigDecimal subtract = wallet.getAmount().subtract(finalAmount);
                if (subtract.compareTo(BigDecimal.ZERO) < 0) {
                    throw new BuyingCurrencyException(String.format("It is not possible to buy %s %s from wallet, in which is %s %s.",
                            finalAmount.toString(), walletCurrencySymbol, wallet.getAmount().toString(), walletCurrencySymbol));
                }

                wallet.setAmount(subtract);
            } else {
                wallet.setAmount(wallet.getAmount().add(finalAmount));
            }

            List<Price> prices = cryptoCurrencyDao.getPricesOfCryptocurrencyInOtherCurrencies(walletCurrencySymbol);
            for (Price price1 : prices) {
                price1.setValue(price1.getValue().multiply(wallet.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            }

            wallet.getCryptoCurrency().setPrices(prices);
        }

        return wallet;
    }

}
