package net.javaGuides.bankingApp.service.impl;

import net.javaGuides.bankingApp.dto.AccountDto;
import net.javaGuides.bankingApp.entity.Account;
import net.javaGuides.bankingApp.mapper.AccountMapper;
import net.javaGuides.bankingApp.repository.AccountRepository;
import net.javaGuides.bankingApp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    //  @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapTOAccount(accountDto);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Account Does Not Esists"));
        return AccountMapper.mapTOAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account = accountRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Account Does Not Exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savesAccount = accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(savesAccount);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account = accountRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Account Does Not Exists"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapTOAccountDto(saveAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapTOAccountDto(account)).
                collect((Collectors.toList()));

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Account Does Not Exists"));

        accountRepository.deleteById(id);

    }
}
