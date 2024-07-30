package com.alfaizuna.virtualthreadjava21;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AccountController {

    public final AccountService accountService;

    @GetMapping("/account/favorites")
    public List<Favorite> getFavorites() {
        String userId = UUID.randomUUID().toString();
        return accountService.getFavorites(userId);
    }

    @GetMapping("/v2/account/favorites")
    public CompletableFuture<List<Favorite>> getFavoritesV2 () {
        String userId = UUID.randomUUID().toString();
        return accountService.getFavoritesV2(userId);
    }
}
