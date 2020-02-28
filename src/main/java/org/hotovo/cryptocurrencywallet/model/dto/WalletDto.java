package org.hotovo.cryptocurrencywallet.model.dto;

public class WalletDto {
    private Long id;
    private String name;
    private String cryptoCurrencySymbol;
    private Float amount;

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

    public String getCryptoCurrencySymbol() {
        return cryptoCurrencySymbol;
    }

    public void setCryptoCurrencySymbol(String cryptoCurrencySymbol) {
        this.cryptoCurrencySymbol = cryptoCurrencySymbol;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
