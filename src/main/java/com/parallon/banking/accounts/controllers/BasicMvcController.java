package com.parallon.banking.accounts.controllers;

import com.parallon.banking.accounts.data.Account;
import com.parallon.banking.accounts.data.AccountRepository;
import com.parallon.banking.accounts.data.Transfer;
import com.parallon.banking.accounts.service.ServiceException;
import com.parallon.banking.accounts.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class BasicMvcController   {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private TransferService service;



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {


        return "login";
    }


    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {
        List<Account> list = repo.findAll();

        model.addAttribute("accountList", list);

        return "accounts";
    }

    @RequestMapping(value = "/viewTransferPage", method = RequestMethod.GET)
    public String viewTransferPage(Model model) {

        Transfer form = new Transfer();

        model.addAttribute("transferFormBean", form);

        return "transferForm";
    }

    @RequestMapping(value = "/processTransfer", method = RequestMethod.POST)
    public String processTransfer(Model model, Transfer form) {


        try {
            service.transfer(form);
        } catch (ServiceException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/viewTransferPage";
        }
        return "redirect:/accounts";
    }



}