package net.javaGuides.bankingApp.service;

import net.javaGuides.bankingApp.dto.AccountDto;
import net.javaGuides.bankingApp.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit (long id,double amount);

    AccountDto withdraw (long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);



}
