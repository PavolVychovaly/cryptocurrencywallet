package org.hotovo.cryptocurrencywallet.model;

import java.util.List;

public class CryptoCurrency {
    private String symbol;
    private List<Price> prices;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
