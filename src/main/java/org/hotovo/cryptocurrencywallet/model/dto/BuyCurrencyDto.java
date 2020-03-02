package org.hotovo.cryptocurrencywallet.model.dto;

import java.math.BigDecimal;

public class BuyCurrencyDto {
    private Long walletId;
    private String walletCurrency;
    private BigDecimal amount;
    private String boughtCurrency;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getWalletCurrency() {
        return walletCurrency;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBoughtCurrency() {
        return boughtCurrency;
    }

    public void setBoughtCurrency(String boughtCurrency) {
        this.boughtCurrency = boughtCurrency;
    }
}
