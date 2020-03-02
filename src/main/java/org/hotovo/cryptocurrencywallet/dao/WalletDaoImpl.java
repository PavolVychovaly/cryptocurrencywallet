package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class WalletDaoImpl implements WalletDao {

    private static Map<Long, Wallet> wallets = new HashMap<>();
    private static AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Wallet create(Wallet wallet) {
        wallet.setId(idCounter.getAndIncrement());
        wallets.put(wallet.getId(), wallet);

        return wallet;
    }

    @Override
    public Wallet findById(Long id) {
        return wallets.get(id);
    }

    @Override
    public void delete(Long id) {
        wallets.remove(id);
    }

    @Override
    public void transferValuesBetweenTwoWallets() {

    }

    @Override
    public List<Wallet> findAll() {
        return new ArrayList(wallets.values());
    }
}
