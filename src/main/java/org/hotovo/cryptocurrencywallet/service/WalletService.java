package org.hotovo.cryptocurrencywallet.service;

import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.model.dto.BuyCurrencyDto;
import org.hotovo.cryptocurrencywallet.model.dto.TransferWalletDto;
import org.hotovo.cryptocurrencywallet.model.dto.WalletDto;
import org.hotovo.cryptocurrencywallet.model.dto.WalletUpdateDto;

import java.util.List;

public interface WalletService {
    Wallet findById(Long id);

    List<Wallet> findAll();

    Wallet create(WalletDto walletDto);

    Wallet update(Long id, WalletUpdateDto dto);

    void delete(Long id);

    Wallet buyCurrency(BuyCurrencyDto dto);

    void transferValuesBetweenTwoWallets(TransferWalletDto dto);
}
