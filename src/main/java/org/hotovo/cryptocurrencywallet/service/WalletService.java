package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.model.Wallet;

public interface WalletService {
    Wallet create(Wallet wallet);

    Wallet findById(Long id);

    Wallet update(Wallet wallet);

    void delete(Long id);

    void transferValuesBetweenTwoWallets();

    Wallet buyCurrency();
}
