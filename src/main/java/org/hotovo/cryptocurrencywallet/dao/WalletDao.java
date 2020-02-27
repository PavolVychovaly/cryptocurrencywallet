package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.Wallet;

public interface WalletDao {
    Wallet create(Wallet wallet);

    Wallet findById();

    Wallet update(Wallet wallet);

    void delete(Long id);

    void transferValuesBetweenTwoWallets();

    Wallet buyCurrency();
}
