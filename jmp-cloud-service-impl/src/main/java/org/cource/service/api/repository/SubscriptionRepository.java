package org.cource.service.api.repository;

import org.cource.dto.Subscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionRepository {

    private final List<Subscription> cache;

    public SubscriptionRepository() {
        cache = new ArrayList<>();
        cache.add(new Subscription("1234", LocalDate.now()));
    }

    public List<Subscription> findAll() {
        return new ArrayList<>(cache);
    }

    public void save(Subscription subscription) {
        cache.add(subscription);
    }

    public Optional<Subscription> findByBankCard(String bankCardNumber) {
        return cache.stream()
                .filter(subs -> subs.getBankCard().equals(bankCardNumber))
                .findFirst();
    }
}
