package com.parallon.banking.accounts.service;


import com.parallon.banking.accounts.data.Account;
import com.parallon.banking.accounts.data.AccountRepository;
import com.parallon.banking.accounts.data.Transfer;
import com.parallon.banking.accounts.data.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TransferService {
    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountRepository accountRepository ;

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, BigDecimal amount) throws ServiceException {
        Optional<Account> dbAcct = accountRepository.findById(id);
        if (!dbAcct.isPresent()) {
            throw new ServiceException("Account not found " + id);
        }


        BigDecimal newBalance = dbAcct.get().getBalance().add(  amount );
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ServiceException(
                    "The money in the account '" + id + "' is not enough (" + dbAcct.get().getBalance() + ")");
        }
        dbAcct.get().setBalance(newBalance);
    }

    // Do not catch BankTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ServiceException.class)
    public void transfer(Transfer t ) throws ServiceException {
        if(t.getFromAccountId() != null)
            addAmount(t.getFromAccountId(), t.getTransferAmount().negate());
        addAmount(t.getToAccountId(), t.getTransferAmount());
        transferRepository.save(t);
    }


    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }
}
