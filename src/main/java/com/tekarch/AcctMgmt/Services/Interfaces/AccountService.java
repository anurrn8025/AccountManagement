package com.tekarch.AcctMgmt.Services.Interfaces;

import com.tekarch.AcctMgmt.Models.Accounts;


public interface AccountService {
    Accounts getAccountDetails(Long accountId);

    Accounts newAccountCreation(Accounts account);

    Accounts updateAccountForUserID(Long userID, Long accountId);

    double getBalanceOfAccount(Long accountId, Long userId);

    Accounts getAccountDetailsByUserID(Long userId);
}
