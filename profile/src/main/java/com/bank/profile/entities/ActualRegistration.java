package com.bank.profile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "actual_registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActualRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(40)", nullable = false)
    private String country;

    @Column(columnDefinition = "varchar(160)")
    private String region;

    @Column(columnDefinition = "varchar(160)")
    private String city;

    @Column(columnDefinition = "varchar(160)")
    private String district;

    @Column(columnDefinition = "varchar(230)")
    private String locality;

    @Column(columnDefinition = "varchar(230)")
    private String street;

    @Column(columnDefinition = "varchar(20)")
    private String houseNumber;

    @Column(columnDefinition = "varchar(20)")
    private String houseBlock;

    @Column(columnDefinition = "varchar(40)")
    private String flatNumber;

    @Column(nullable = false)
    private Long index;

    @OneToOne(mappedBy = "actualRegistration", fetch = FetchType.LAZY)
    private Profile profile;
}
