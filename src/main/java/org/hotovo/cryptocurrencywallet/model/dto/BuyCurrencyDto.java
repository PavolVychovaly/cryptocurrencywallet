package org.hotovo.cryptocurrencywallet.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BuyCurrencyDto {

    @NotNull(message = "Wallet id may not be null.")
    private Long walletId;

    @NotNull(message = "Amount in wallet may not be null.")
    private BigDecimal amount;

    @NotEmpty(message = "Currency symbol of amount may not be null.")
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
