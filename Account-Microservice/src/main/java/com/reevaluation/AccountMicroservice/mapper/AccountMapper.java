package com.reevaluation.AccountMicroservice.mapper;

import com.reevaluation.AccountMicroservice.dto.AccountDTO;
import com.reevaluation.AccountMicroservice.entity.Account;

public class AccountMapper {

    public static AccountDTO mapToAccountDto(Account account){
        AccountDTO accountDto = new AccountDTO(
                account.getId(),
                account.getAccountName(),
                account.getAccountDescription(),
                account.getAccountCode(),
                account.getCreatedDate()
        );
        return accountDto;
    }

    public static Account mapToAccount(AccountDTO accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountName(),
                accountDto.getAccountDescription(),
                accountDto.getAccountCode(),
                accountDto.getCreatedDate()
        );
        return account;
    }
}
