package org.hotovo.cryptocurrencywallet.model.dto;

import java.math.BigDecimal;

public class WalletDto {
    private Long id;
    private String name;
    private BigDecimal amount;
    private String cryptoCurrencySymbol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCryptoCurrencySymbol() {
        return cryptoCurrencySymbol;
    }

    public void setCryptoCurrencySymbol(String cryptoCurrencySymbol) {
        this.cryptoCurrencySymbol = cryptoCurrencySymbol;
    }

}
