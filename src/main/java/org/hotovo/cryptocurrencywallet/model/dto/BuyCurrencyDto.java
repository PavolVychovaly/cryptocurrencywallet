package org.hotovo.cryptocurrencywallet.model.dto;

import javax.validation.constraints.NotNull;

public class BuyCurrencyDto extends BaseDto {

    @NotNull(message = "Wallet id may not be null.")
    private Long walletId;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
}
