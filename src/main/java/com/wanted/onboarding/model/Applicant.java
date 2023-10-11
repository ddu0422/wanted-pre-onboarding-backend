package com.wanted.onboarding.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    uniqueConstraints = {
        @UniqueConstraint(name = "idx_applicant_u1", columnNames = {"member_id", "recruitment_id"})
    }
)
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Builder(access = AccessLevel.PRIVATE)
    private Applicant(Member member, Recruitment recruitment) {
        this.member = member;
        this.recruitment = recruitment;
    }

    public static Applicant of(Member member, Recruitment recruitment) {
        return Applicant.builder()
            .member(member)
            .recruitment(recruitment)
            .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(member, applicant.member) && Objects.equals(recruitment, applicant.recruitment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, recruitment);
    }
}
