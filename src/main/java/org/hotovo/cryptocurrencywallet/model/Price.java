package org.hotovo.cryptocurrencywallet.model;

public class Price {
    private String code;
    private Float value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
