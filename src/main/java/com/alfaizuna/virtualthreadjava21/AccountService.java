package com.alfaizuna.virtualthreadjava21;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final Executor taskExecutor;

    public CompletableFuture<List<Favorite>> getFavoritesV2(String userId) {
        return CompletableFuture.supplyAsync(()->getFavorites(userId), taskExecutor );
    }

    public List<Favorite> getFavorites(String userId){
        log.info("start getFavorites : {} - {}", Thread.currentThread(), userId);
        RestClient restClient = RestClient.create();
        List<Favorite> favorites = restClient.get().uri("http://localhost:3001/favorites").retrieve()
                .toEntity(new ParameterizedTypeReference<List<Favorite>>() {
                }).getBody();
        log.info("end getFavorites : {} - {}", Thread.currentThread(), userId);
        return favorites;
    }
}
