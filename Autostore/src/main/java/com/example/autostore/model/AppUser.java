package com.example.autostore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppUser {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Column(name = "userEmail", unique = true)
    private String userEmail;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userFullName")
    private String userFullName;

    @Column(name = "userIsActive")
    private Boolean userIsActive = true;

    private String role;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Customer customer;


}
