package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.Wallet;

import java.util.List;

public interface WalletDao {
    Wallet create(Wallet wallet);

    Wallet findById(Long id);

    void delete(Long id);

    void transferValuesBetweenTwoWallets();

    Wallet buyCurrency();

    List<Wallet> findAll();
}
