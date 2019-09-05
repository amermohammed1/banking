package com.parallon.banking.accounts.controllers;

import com.parallon.banking.accounts.controllers.exceptions.InvalidOperationException;
import com.parallon.banking.accounts.data.Account;
import com.parallon.banking.accounts.data.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest/accounts")
public class AccountController {

    @Autowired
    private AccountRepository repo;


    @PostConstruct
    void initDatabaseWithSampleUsers(){
        saveAccount(new Account(1,"John", "Tiger", "j@t.com", BigDecimal.TEN));
        saveAccount(new Account(2,"Mike", "Morris", "m@t.com", BigDecimal.ZERO));
        saveAccount(new Account(3,"Ivan", "Roger", "r@t.com", BigDecimal.ONE));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts() {
        List<Account> Accounts = repo.findAll();
        return Accounts;
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAccount(Account a) {
        repo.save(a);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable(name = "id") Long id) {
        Optional<Account> account = repo.findById(id);
        if(account.isPresent())
            return account.get();
        throw new InvalidOperationException("Invalid id");
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(name = "id") Long id) {
        Optional<Account> acc = repo.findById(id);
        if (acc.isPresent()) {
            repo.delete(acc.get());
        } else {
            throw new InvalidOperationException("Invalid id");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@RequestBody Account account,
                              @PathVariable(name = "id") Long id) {
        Account accDB = getAccount(id);
        accDB.setEmail(account.getEmail());
        accDB.setFirstName(account.getFirstName());
        accDB.setLastName(account.getLastName());
        repo.save(accDB);
    }


    

}
