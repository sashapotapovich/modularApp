package org.cource.api.impl;

import org.cource.api.BankInterface;
import org.cource.api.repository.BankCardRepository;
import org.cource.dto.BankCard;
import org.cource.dto.BankCardType;
import org.cource.dto.CreditBankCard;
import org.cource.dto.DebitBankCard;
import org.cource.dto.User;

import java.util.UUID;

public class BankInterfaceImpl implements BankInterface {

    private BankCardRepository bankCardRepository = new BankCardRepository();

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        switch (bankCardType) {
            case CREDIT -> {
                var creditBankCard = new CreditBankCard(UUID.randomUUID().toString(), user);
                bankCardRepository.save(creditBankCard);
                return creditBankCard;
            }
            case DEBIT -> {
                var debitBankCard = new DebitBankCard(UUID.randomUUID().toString(), user);
                bankCardRepository.save(debitBankCard);
                return debitBankCard;
            }
            default -> throw new IllegalStateException("Unexpected value: " + bankCardType);
        }
    }

    @Override
    public BankCard findCardForUser(User user) {
        return bankCardRepository.findCardByUsername(user.getUsername());
    }

    public void setBankCardRepository(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }
}
