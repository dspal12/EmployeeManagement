package com.reevaluation.AccountMicroservice.controller;


import com.reevaluation.AccountMicroservice.dto.AccountDTO;
import com.reevaluation.AccountMicroservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")

public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/post")
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDto){
        AccountDTO savedAccount = accountService.saveAccount(accountDto);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    // Build Get Organization by Code REST API
    @GetMapping("/get/{code}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("code") String accountCode){
        AccountDTO accountDto = accountService.getAccountByCode(accountCode);
        return ResponseEntity.ok(accountDto);
    }

}
