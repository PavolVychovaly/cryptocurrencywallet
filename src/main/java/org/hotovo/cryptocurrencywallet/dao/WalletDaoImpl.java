package org.hotovo.cryptocurrencywallet.dao;

import org.hotovo.cryptocurrencywallet.common.exception.DataNotFoundException;
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
        Wallet wallet = wallets.get(id);
        if (wallet == null) {
            throw new DataNotFoundException(String.format("Wallet with ID = %d does not exist.", id));
        }

        return wallet;
    }

    @Override
    public void delete(Long id) {
        Wallet wallet = findById(id);
        wallets.remove(wallet.getId());
    }

    @Override
    public List<Wallet> findAll() {
        return new ArrayList(wallets.values());
    }
}
