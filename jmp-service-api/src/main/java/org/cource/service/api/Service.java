package org.cource.service.api;

import org.cource.dto.BankCard;
import org.cource.dto.Subscription;
import org.cource.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    static boolean isPayableUser(User user) {
        int yearOfBirth = user.getBirthday().getYear();
        int yearNow = LocalDate.now().getYear();
        return yearNow - yearOfBirth >= 18;
    }

    default double getAverageUsersAge() {
        final var now = LocalDate.now();
        return getAllUsers().stream()
                .map(User::getBirthday)
                .map(birthDay -> now.minus(birthDay.getYear(), ChronoUnit.YEARS))
                .mapToLong(LocalDate::getYear)
                .average()
                .orElseThrow(() -> new RuntimeException("Average users age is empty"));
    }

    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber);
    List<User> getAllUsers();
    User findUser(String username);
    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

    void addUser(String username, String lastName, LocalDate birthday);
}
