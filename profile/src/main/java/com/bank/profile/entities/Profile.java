package com.bank.profile.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long  phoneNumber;

    @Column(columnDefinition = "varchar(264)")
    private String email;

    @Column(columnDefinition = "varchar(370)")
    private String nameOnCard;

    @Column(unique = true)
    private Long inn;

    @Column(unique = true)
    private Long snils;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "actual_registration_id", referencedColumnName = "id")
    private ActualRegistration actualRegistration;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AccountDetails> accountDetails = new ArrayList<>();
}
