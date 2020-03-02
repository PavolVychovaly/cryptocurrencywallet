package org.hotovo.cryptocurrencywallet.model.dto;

import java.math.BigDecimal;

public class TransferWalletDto {
    private Long fromWalletId;
    private Long toWalletId;
    private BigDecimal amount;
    private String currencyOfAmount;

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public void setFromWalletId(Long fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public Long getToWalletId() {
        return toWalletId;
    }

    public void setToWalletId(Long toWalletId) {
        this.toWalletId = toWalletId;
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
