package org.cource.service.api.impl;

import org.cource.dto.BankCard;
import org.cource.dto.Subscription;
import org.cource.dto.User;
import org.cource.service.api.Service;
import org.cource.service.api.repository.SubscriptionRepository;
import org.cource.service.api.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    private SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private UserRepository userRepository = new UserRepository();

    @Override
    public void subscribe(BankCard bankCard) {
        var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptionRepository.save(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber) {
        return subscriptionRepository.findByBankCard(bankCardNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Unable to find User with username - " + username));
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptionRepository.findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(String username, String lastName, LocalDate birthday) {
        userRepository.save(new User(username, lastName, birthday));
    }

    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
