package org.hotovo.cryptocurrencywallet.model.dto;

import javax.validation.constraints.NotNull;

public class TransferWalletDto extends BaseDto {

    @NotNull(message = "From wallet id may not be null.")
    private Long fromWalletId;

    @NotNull(message = "To wallet id may not be null.")
    private Long toWalletId;

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
}
