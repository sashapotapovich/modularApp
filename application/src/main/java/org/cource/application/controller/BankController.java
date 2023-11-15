package org.cource.application.controller;

import org.cource.api.BankInterface;
import org.cource.application.exception.SubscribtionNotFoundException;
import org.cource.dto.BankCardType;
import org.cource.dto.Subscription;
import org.cource.dto.User;
import org.cource.service.api.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ServiceLoader;

@RestController
@RequestMapping("/api")
public class BankController {

    private final BankInterface bankInterface = ServiceLoader.load(BankInterface.class).findFirst().get();
    private final Service service = ServiceLoader.load(Service.class).findFirst().get();

    @PostMapping(value = "/createCard")
    @ResponseBody
    public void createBankCard(String username, String bankCardType) {
        var user = service.findUser(username);
        var bankCardTypeEnum = BankCardType.valueOf(bankCardType);
        if (Service.isPayableUser(user)) {
            bankInterface.createBankCard(user, bankCardTypeEnum);
        }
    }

    @PostMapping(value = "/createUser")
    public void createUser(String username, String lastName, LocalDate birthday) {
        service.addUser(username, lastName, birthday);
    }

    @GetMapping("/getUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping(value = "/createSubs")
    public void createSubscription(String username) {
        var user = service.findUser(username);
        var cardForUser = bankInterface.findCardForUser(user);
        service.subscribe(cardForUser);
    }

    @GetMapping("/averageUsersAge")
    @ResponseBody
    public String getAverageUsersAge() {
        var age = service.getAverageUsersAge();
        return "Average age - " + age + " years";
    }

    @GetMapping("/getSubsByNumber/{bankCardNumber}")
    @ResponseBody
    public Subscription getSubsByNumber(@PathVariable("bankCardNumber") String bankCardNumber) {
        return service.getSubscriptionByBankCardNumber(bankCardNumber)
                .orElseThrow(() -> new SubscribtionNotFoundException("Subscription not found"));
    }

    @GetMapping("/getSubsBeforeDate")
    @ResponseBody
    public List<Subscription> getSubsBeforeDate(@RequestParam String isoDate) {
        LocalDate date = LocalDate.parse(isoDate, DateTimeFormatter.ISO_DATE);
        return service.getAllSubscriptionsByCondition(subs -> subs.getStartDate().isBefore(date));
    }
}
