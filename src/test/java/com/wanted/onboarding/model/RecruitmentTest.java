package com.wanted.onboarding.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RecruitmentTest {

    private Recruitment recruitment;

    @BeforeEach
    void setUp() {
        recruitment = Recruitment.of("before position", 1000, "before description", "before skill", new Company());
    }

    @Test
    @DisplayName("채용포지션만 변경할 수 있다")
    void test1() {
        recruitment.change("after position", null, null, null);

        assertAll(
            () -> assertEquals("after position", recruitment.getPosition()),
            () -> assertEquals(BigDecimal.valueOf(1000), recruitment.getReward()),
            () -> assertEquals("before description", recruitment.getDescription()),
            () -> assertEquals("before skill", recruitment.getSkill())
        );
    }

    @Test
    @DisplayName("채용보상금만 변경할 수 있다")
    void test2() {
        recruitment.change(null, 100000, null, null);

        assertAll(
            () -> assertEquals("before position", recruitment.getPosition()),
            () -> assertEquals(BigDecimal.valueOf(100000), recruitment.getReward()),
            () -> assertEquals("before description", recruitment.getDescription()),
            () -> assertEquals("before skill", recruitment.getSkill())
        );
    }

    @Test
    @DisplayName("채용내용만 변경할 수 있다")
    void test3() {
        recruitment.change(null, null, "after description", null);

        assertAll(
            () -> assertEquals("before position", recruitment.getPosition()),
            () -> assertEquals(BigDecimal.valueOf(1000), recruitment.getReward()),
            () -> assertEquals("after description", recruitment.getDescription()),
            () -> assertEquals("before skill", recruitment.getSkill())
        );
    }

    @Test
    @DisplayName("사용기술만 변경할 수 있다")
    void test4() {
        recruitment.change(null, null, null, "after skill");

        assertAll(
            () -> assertEquals("before position", recruitment.getPosition()),
            () -> assertEquals(BigDecimal.valueOf(1000), recruitment.getReward()),
            () -> assertEquals("before description", recruitment.getDescription()),
            () -> assertEquals("after skill", recruitment.getSkill())
        );
    }

    @Test
    @DisplayName("여러 정보를 변경할 수 있다")
    void test5() {
        recruitment.change(null, 100000, null, "after skill");

        assertAll(
            () -> assertEquals("before position", recruitment.getPosition()),
            () -> assertEquals(BigDecimal.valueOf(100000), recruitment.getReward()),
            () -> assertEquals("before description", recruitment.getDescription()),
            () -> assertEquals("after skill", recruitment.getSkill())
        );
    }
}
