package org.cource.dto;

import java.time.LocalDate;

public class User {

    private String username;
    private String surname;
    private LocalDate birthday;

    public User() {
    }

    public User(String username, String surname, LocalDate birthday) {
        this.username = username;
        this.surname = surname;
        this.birthday = birthday;
    }


    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
