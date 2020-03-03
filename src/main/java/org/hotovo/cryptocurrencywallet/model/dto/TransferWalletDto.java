package org.hotovo.cryptocurrencywallet.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferWalletDto {

    @NotNull(message = "From wallet id may not be null.")
    private Long fromWalletId;

    @NotNull(message = "To wallet id may not be null.")
    private Long toWalletId;

    @NotNull(message = "Amount in wallet may not be null.")
    private BigDecimal amount;

    @NotEmpty(message = "Currency symbol of amount may not be null.")
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
