package org.hotovo.cryptocurrencywallet.view;

import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.model.dto.BuyCurrencyDto;
import org.hotovo.cryptocurrencywallet.model.dto.TransferWalletDto;
import org.hotovo.cryptocurrencywallet.model.dto.WalletDto;
import org.hotovo.cryptocurrencywallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> findAll() {

        return ResponseEntity.ok(walletService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(walletService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Wallet> create(@Valid @RequestBody WalletDto walletDto) {

        return ResponseEntity.ok(walletService.create(walletDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Wallet> update(@PathVariable("id") Long id, @Valid @RequestBody WalletDto walletDto) {
        return ResponseEntity.ok(walletService.update(id, walletDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        walletService.delete(id);

        return ResponseEntity.ok(String.format("Wallet with ID %d successfully deleted.", id));
    }

    @PostMapping("/buyCurrency")
    public Wallet buyCurrency(@Valid @RequestBody BuyCurrencyDto dto) {
        return walletService.buyCurrency(dto);
    }

    @PostMapping("/transferValues")
    public ResponseEntity<String> transferValuesBetweenTwoWallets(@Valid @RequestBody TransferWalletDto dto) {
        walletService.transferValuesBetweenTwoWallets(dto);

        return ResponseEntity.ok(String.format("From wallet with ID %d to wallet with ID %d was transfered %s %s.",
                dto.getFromWalletId(), dto.getToWalletId(), dto.getAmount(), dto.getCurrencyOfAmount()));
    }
}
