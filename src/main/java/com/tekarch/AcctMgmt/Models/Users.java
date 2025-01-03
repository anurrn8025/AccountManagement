package com.tekarch.AcctMgmt.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(columnDefinition = "varchar(50)", unique=true,nullable = false)
    private String username;
    @Column(columnDefinition = "varchar(100)", unique=true,nullable = false)
    private String email;
    @Column(columnDefinition = "text",nullable = false)
    private String password_hash;
    @Column(columnDefinition = "varchar(15)",nullable = true,unique=true)
    private String phone_number;
    @Column(columnDefinition = "boolean default false",nullable = false)
    private boolean two_factor_enabled;
    @Column(columnDefinition = "varchar(20) default 'pending'",nullable = false)
    private String kyc_status;
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP",nullable = false)
    private String created_at;
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP",nullable = false)
    private String updated_at;
    @PrePersist
    public void prePersist() {
        if (kyc_status == null) {
            kyc_status = "pending";
        }
        if (created_at == null) {
            created_at = new Date().toString();
        }
        if (updated_at == null) {
            updated_at = new Date().toString();;
        }
    }


}
