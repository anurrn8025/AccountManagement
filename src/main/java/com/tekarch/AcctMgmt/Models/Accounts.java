package com.tekarch.AcctMgmt.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @ManyToOne
   // @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JoinColumn(name = "user_id")
    private Users users;

    /*@Column(columnDefinition = "int", unique = true, nullable = false)
    private Long user_id;*/

    @Column(columnDefinition = "varchar(20)", unique = true, nullable = false)
    private String account_number;
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String account_type;

    @Column(columnDefinition = "double default 0.0")
    private double balance;

    @Column(columnDefinition = "varchar(10) default 'USD'")
    private String currency;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private String created_at;


    @PrePersist
    public void prePersist() {

        if (currency == null) {
            currency = "USD";
        }
        if (created_at == null) {
            created_at = new Date().toString();

        }
    }


}
