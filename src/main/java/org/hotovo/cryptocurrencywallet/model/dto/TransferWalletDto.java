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

    @NotEmpty(message = "Currency symbol may not be null.")
    private String currencySymbol;

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

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
