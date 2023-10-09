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
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;

    @Column(name = "position", length = 100, nullable = false)
    private String position;

    @Column(name = "reward", nullable = false)
    private BigDecimal reward;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "skill", length = 50, nullable = false)
    private String skill;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;

    private Recruitment(String position, BigDecimal reward, String description, String skill, Company company) {
        this.position = position;
        this.reward = reward;
        this.description = description;
        this.skill = skill;
        this.company = company;
    }

    public static Recruitment of(String position, Integer reward, String description, String skill, Company company) {
        return new Recruitment(position, BigDecimal.valueOf(reward), description, skill, company);
    }

    public void change(String position, Integer reward, String description, String skill) {
        this.position = Objects.requireNonNullElse(position, this.position);
        this.reward = reward != null ? BigDecimal.valueOf(reward) : this.reward;
        this.description = Objects.requireNonNullElse(description, this.description);
        this.skill = Objects.requireNonNullElse(skill, this.skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruitment that = (Recruitment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
