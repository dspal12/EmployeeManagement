package com.reevaluation.AccountMicroservice.service;

import com.reevaluation.AccountMicroservice.dto.AccountDTO;
import com.reevaluation.AccountMicroservice.entity.Account;
import com.reevaluation.AccountMicroservice.mapper.AccountMapper;
import com.reevaluation.AccountMicroservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;


    public AccountDTO saveAccount(AccountDTO accountDto) {

        // convert OrganizationDto into Organization jpa entity
        Account account = AccountMapper.mapToAccount(accountDto);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }


    public AccountDTO getAccountByCode(String accountCode) {
        Account account = accountRepository.findByAccountCode(accountCode);
        return AccountMapper.mapToAccountDto(account);
    }
}
