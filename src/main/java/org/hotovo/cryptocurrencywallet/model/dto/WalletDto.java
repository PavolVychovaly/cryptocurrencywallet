package org.hotovo.cryptocurrencywallet.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WalletDto {

    @NotEmpty(message = "Name of wallet may not be null.")
    private String name;

    @NotNull(message = "Amount in wallet may not be null.")
    private BigDecimal amount;

    @NotEmpty(message = "Currency symbol may not be null.")
    private String currencySymbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
