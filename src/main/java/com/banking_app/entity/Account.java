package com.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Account_Holder_Name")
    private String name;
    @Column(name = "Total_Balance")
    private Double balance=0.0;
    @Column(name = "Account_Number",unique = true, nullable = false, length = 11)
    @JsonIgnore
    private Long accountNumber;
    @Column(name = "Account_Type")
    private String accountType ="Saving";
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Email")
    private String email;
}
