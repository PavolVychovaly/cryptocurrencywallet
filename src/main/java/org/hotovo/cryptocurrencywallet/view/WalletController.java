package org.hotovo.cryptocurrencywallet.view;

import org.hotovo.cryptocurrencywallet.model.Wallet;
import org.hotovo.cryptocurrencywallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{id}")
    public Wallet findById(@PathVariable("id") Long id) {
        return walletService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet create(@RequestBody Wallet wallet) {
        return walletService.create(wallet);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Wallet update(@PathVariable( "id" ) Long id, @RequestBody Wallet wallet) {
        return walletService.update(wallet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        walletService.delete(id);
    }

    @PostMapping("/buyCurrency")
    public Wallet buyCurrency() {
        return walletService.buyCurrency();
    }

    @PostMapping("/transferValues")
    public void transferValuesBetweenTwoWallets() {
        walletService.transferValuesBetweenTwoWallets();
    }
}
