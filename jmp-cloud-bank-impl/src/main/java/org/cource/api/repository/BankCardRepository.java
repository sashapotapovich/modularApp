package org.cource.api.repository;

import org.cource.dto.BankCard;
import org.cource.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankCardRepository {
    private final List<BankCard> cache;

    public BankCardRepository() {
        cache = new ArrayList<>();
        cache.add(new BankCard("1111", new User("test", "test", LocalDate.now())));
    }

    public void save(BankCard bankCard) {
        cache.add(bankCard);
    }

    public BankCard findCardByUsername(String username) {
        return cache.stream()
                .filter(bankCard ->
                        bankCard.getUser().getUsername().equalsIgnoreCase(username)
                )
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Bank card is not found for user with username - " + username)
                );
    }
}
