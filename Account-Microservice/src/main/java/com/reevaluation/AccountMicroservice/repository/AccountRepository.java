package com.reevaluation.AccountMicroservice.repository;

import com.reevaluation.AccountMicroservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountCode(String accountCode);
}
