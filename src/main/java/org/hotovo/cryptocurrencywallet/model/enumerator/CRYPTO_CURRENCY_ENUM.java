package org.hotovo.cryptocurrencywallet.model.enumerator;

public enum CRYPTO_CURRENCY_ENUM {
    BITCOIN("BTC"), ETHEREUM("ETH"), XRP("XRP"), BITCOIN_CASH("BCH"), TETHER("USDT");

    private String value;

    CRYPTO_CURRENCY_ENUM(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
