package org.cource.service.api.repository;

import org.cource.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final List<User> cache;

    public UserRepository() {
        cache = new ArrayList<>();
        cache.add(new User("test", "test", LocalDate.now().minusYears(10)));
    }

    public List<User> findAll() {
        return new ArrayList<>(cache);
    }

    public Optional<User> findByUserName(String userName) {
        return cache.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(userName))
                .findFirst();
    }

    public void save(User user) {
        cache.add(user);
    }
}
