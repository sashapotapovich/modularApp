package org.cource.api;

import org.cource.dto.BankCard;
import org.cource.dto.BankCardType;
import org.cource.dto.User;

public interface BankInterface {
    BankCard createBankCard(User user, BankCardType bankCardType);

    BankCard findCardForUser(User user);
}
