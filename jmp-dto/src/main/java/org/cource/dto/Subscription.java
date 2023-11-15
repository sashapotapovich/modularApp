package org.cource.dto;

import java.time.LocalDate;

public class Subscription {

    private String bankCard;
    private LocalDate startDate;

    public Subscription() {
    }

    public Subscription(String bankCard, LocalDate startDate) {
        this.bankCard = bankCard;
        this.startDate = startDate;
    }

    public String getBankCard() {
        return bankCard;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
