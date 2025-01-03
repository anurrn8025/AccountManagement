package com.tekarch.AcctMgmt.Repositories;

import com.tekarch.AcctMgmt.Models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository <Accounts,Long> {

   // public double findBalanceByAccountidAndUserid(Long accountId, Users userId) ;

   // @Query("SELECT balance FROM Accounts WHERE account_id =:accountId AND user_id=:userId")
    @Query("SELECT o.balance FROM Accounts o WHERE o.account_id =:accountId AND o.users.user_id=:userId")
    //SELECT o FROM Order o WHERE o.customer.id = :customerId
    double findBalanceByAccountidAndUserid(@Param("accountId") Long accountId,@Param("userId") Long userId);

    @Query("SELECT o.balance FROM Accounts o WHERE o.users.user_id=:userId")
    double findBalanceByUserid(@Param("userId") Long userId);



}
