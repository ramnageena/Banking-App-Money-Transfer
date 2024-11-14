package com.banking_app.entity;

import com.banking_app.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Name is required")
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
    @Email(message = "Please provide the valid email format")
    @NotNull(message = "Email is required")
    @Column(name = "Email")
    private String email;
}
