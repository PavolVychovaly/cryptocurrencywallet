package org.hotovo.cryptocurrencywallet.model.dto;

import java.math.BigDecimal;

public class BuyCurrencyDto {
    private Long walletId;
    private BigDecimal amount;
    private String currencyOfAmount;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyOfAmount() {
        return currencyOfAmount;
    }

    public void setCurrencyOfAmount(String currencyOfAmount) {
        this.currencyOfAmount = currencyOfAmount;
    }
}
