package org.hotovo.cryptocurrencywallet.model;

import java.util.List;

public class CryptoCurrency {
    private String code;
    private List<Price> prices;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
