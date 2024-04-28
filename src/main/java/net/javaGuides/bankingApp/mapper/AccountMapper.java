package net.javaGuides.bankingApp.mapper;

import net.javaGuides.bankingApp.dto.AccountDto;
import net.javaGuides.bankingApp.entity.Account;

public class AccountMapper {
    public static Account mapTOAccount(AccountDto accountDto){
        Account account=new Account(
                accountDto.getId(),
                accountDto.getAcHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapTOAccountDto(Account account){
        AccountDto accountDto =new AccountDto(
                account.getId(),
                account.getAcHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
