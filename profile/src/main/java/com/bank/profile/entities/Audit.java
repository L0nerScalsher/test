package com.bank.profile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(40)", nullable = false)
    private String entityType;

    @Column(nullable = false)
    private String operationType;

    @Column(nullable = false)
    private String createdBy;

    private String modifiedBy;

    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime modifiedAt;

    @Column(columnDefinition = "text")
    private String newEntityJson;

    @Column(columnDefinition = "text", nullable = false)
    private String entityJson;
}
