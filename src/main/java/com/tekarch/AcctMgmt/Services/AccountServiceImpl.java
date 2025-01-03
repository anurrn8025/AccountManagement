package com.tekarch.AcctMgmt.Services;

import com.tekarch.AcctMgmt.Models.Accounts;
import com.tekarch.AcctMgmt.Models.Users;
import com.tekarch.AcctMgmt.Repositories.AccountRepository;
import com.tekarch.AcctMgmt.Services.Interfaces.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Value("${microservice.api.url}")
    private String microserviceUrl;

    @Autowired
    private RestTemplate restTemplate;

    private final AccountRepository accountRepository;

    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Accounts getAccountDetails(Long accountId) {
        System.out.println("get acct details");
        return accountRepository.findById(accountId).orElse(null);

    }

    @Override
    public Accounts newAccountCreation(Accounts account) {
        System.out.println("New request for new account creation");
        logger.info("New request for account creation in bofa");
       /* Optional<Accounts> existingAccount = accountRepository.findById(account.getAccount_id());

        if (existingAccount.isPresent()) {
            new RuntimeException("Account already exists");
        }
else{*/
    //check if user exists

            String serviceBUrl = "http://localhost:8081/users/" + account.getUsers().getUser_id();
           // ResponseEntity<String> response = restTemplate.getForEntity(serviceBUrl, String.class);
        //Users users= restTemplate.getForObject(serviceBUrl, Users.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Users> response = restTemplate.exchange(serviceBUrl, HttpMethod.GET, entity, Users.class);


        HttpStatusCode statusCode = response.getStatusCode();
           // Users users = response.getBody();
            if (statusCode == HttpStatus.OK) {
                System.out.println("user Exists!");
            } else {
                System.out.println("Request failed with status: " + statusCode);
                System.out.println("User does not exist");

            }



        account.setUsers(response.getBody());
        return accountRepository.save(account);
    }

    @Override
    public Accounts updateAccountForUserID(Long userID, Long accountId) {
        Accounts account = new Accounts();
        account.setAccount_id(accountId);
        System.out.println("update account request for particular userid");
        logger.info("update account request for particular userid");
        return accountRepository.save(account);
    }




    @Override
    public double getBalanceOfAccount(Long userId, Long accountId) {
        System.out.println("get Balance for particular accountid and userid");
        logger.info("get Balance for particular accountid and userid");
        return accountRepository.findBalanceByAccountidAndUserid(userId,accountId);
    }



    @Override
    public Accounts getAccountDetailsByUserID(Long userId) {
        System.out.println("get Account Details By using userid");
        logger.info("get Account Details By using userid");
        return accountRepository.findById(userId).orElse(null);
    }


}
