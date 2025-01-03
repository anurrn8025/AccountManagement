package com.tekarch.AcctMgmt.Controllers;

import com.tekarch.AcctMgmt.Models.Accounts;
import com.tekarch.AcctMgmt.Services.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AcctController {

    private final AccountServiceImpl accountServiceImpl;

    public AcctController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    //To create an account
    @PostMapping("/account")
    public ResponseEntity<Accounts> newAccountCreation(@RequestBody Accounts account){
        return new ResponseEntity<>(accountServiceImpl.newAccountCreation(account), HttpStatus.CREATED);
    }

    @PutMapping("/account/{userId}/{accountId}")
    public ResponseEntity<Accounts> updateUser(@PathVariable("userId")  Long userID,@PathVariable("accountId")  Long accountId){

        return new ResponseEntity<>(accountServiceImpl.updateAccountForUserID(userID,accountId), HttpStatus.ACCEPTED);
    }

    //Retrieving single account with AccountID
    @GetMapping("/account/{accountId}")
    public Accounts getAccountDetails(@PathVariable("accountId")  Long accountId)
    {
        return accountServiceImpl.getAccountDetails(accountId);
    }

    //get balance of user using userID and ac
    @GetMapping("/account/{userId}/{accountId}")
    public double getBalanceOfAccount(@PathVariable("userId")  Long userId,@PathVariable("accountId") Long accountId)
    {
        return accountServiceImpl.getBalanceOfAccount(userId,accountId);
    }

    //Retrieving single account with userId
    @GetMapping("/account/{userId}")
    public Accounts getAccountDetailsByUserID(@PathVariable("userId")  Long userId)
    {
        return accountServiceImpl.getAccountDetailsByUserID(userId);
    }




}
