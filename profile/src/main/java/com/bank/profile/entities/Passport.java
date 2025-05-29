package com.bank.profile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "passport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer series;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(columnDefinition = "varchar(3)", nullable = false)
    private String gender;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate birthDate;

    @Column(columnDefinition = "varchar(480)", nullable = false)
    private String birthPlace;

    @Column(columnDefinition = "text", nullable = false)
    private String issuedBy;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate dateOfIssue;

    @Column(nullable = false)
    private Integer divisionCode;

    @Column(columnDefinition = "date")
    private LocalDate expirationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id", referencedColumnName = "id", nullable = false)
    private Registration registration;

}
