package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletDaoImpl implements WalletDao {

    @Override
    public Wallet create(Wallet wallet) {
        return null;
    }

    @Override
    public Wallet findById() {
        return null;
    }

    @Override
    public Wallet update(Wallet wallet) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void transferValuesBetweenTwoWallets() {

    }

    @Override
    public Wallet buyCurrency() {
        return null;
    }
}
