package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.dao.WalletDao;
import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    @Override
    public Wallet create(Wallet wallet) {
        return walletDao.create(wallet);
    }

    @Override
    public Wallet findById(Long id) {
        return walletDao.findById();
    }

    @Override
    public Wallet update(Wallet wallet) {
        return walletDao.update(wallet);
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
