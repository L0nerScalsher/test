package com.bank.profile.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(166)", nullable = false)
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

    @OneToOne(mappedBy = "registration", fetch = FetchType.LAZY)
    private Passport passport;
}
