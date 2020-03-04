package org.hotovo.cryptocurrencywallet.model.dto;

import javax.validation.constraints.NotEmpty;

public class WalletDto extends BaseDto {

    @NotEmpty(message = "Name of wallet may not be null.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
