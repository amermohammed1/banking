package com.parallon.banking.accounts.controllers;

 import com.parallon.banking.accounts.controllers.exceptions.InvalidOperationException;
 import com.parallon.banking.accounts.controllers.exceptions.TransferFailedException;
 import com.parallon.banking.accounts.data.Account;
 import com.parallon.banking.accounts.data.Transfer;
 import com.parallon.banking.accounts.service.ServiceException;
 import com.parallon.banking.accounts.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;
 import java.util.Optional;


@RestController
@RequestMapping("/rest/transfers")
public class TransferController  {


    @Autowired
    private TransferService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getAccounts() {
        return service.findAll();
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void transfer(@RequestBody Transfer t) {
         try {
            service.transfer(t);
        } catch (ServiceException e) {
            throw new TransferFailedException("Operation Failed");
        }

     }


    @PostMapping("/creditAccount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void creditAccount(@RequestBody Transfer t) {
        try {
            service.transfer(t);
        } catch (ServiceException e) {
            throw new TransferFailedException("Operation Failed");
        }

    }

    @PostMapping("/debitAccount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void debitAccount(@RequestBody Transfer t) {
        try {
            service.transfer(t);
        } catch (ServiceException e) {
            throw new TransferFailedException("Operation Failed");
        }

    }



}