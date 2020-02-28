package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.model.dto.WalletDto;

import java.util.List;

public interface WalletService {
    Wallet create(WalletDto walletDto);

    Wallet findById(Long id);

    Wallet update(WalletDto walletDto);

    void delete(Long id);

    void transferValuesBetweenTwoWallets();

    Wallet buyCurrency();

    List<Wallet> findAll();
}
