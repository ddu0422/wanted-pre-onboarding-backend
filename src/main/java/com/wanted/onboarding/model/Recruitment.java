package com.wanted.onboarding.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;

    @Column(name = "position", length = 100, nullable = false)
    private String position;

    @Column(name = "reward", nullable = false)
    private BigDecimal bigDecimal;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "skill", length = 50, nullable = false)
    private String skill;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;
}
