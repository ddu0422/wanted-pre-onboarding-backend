package com.wanted.onboarding.usecase.exception;

public class RecruitmentNotFoundException extends RuntimeException {

    private static final String MESSAGE = "채용공고를 찾을 수 없습니다.";

    public RecruitmentNotFoundException() {
        super(MESSAGE);
    }
}
