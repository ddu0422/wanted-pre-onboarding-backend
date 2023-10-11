package com.wanted.onboarding.usecase.exception;

public class AlreadyAppliedForRecruitmentException extends RuntimeException {

    private static final String MESSAGE = "이미 채용공고에 지원했습니다.";

    public AlreadyAppliedForRecruitmentException() {
        super(MESSAGE);
    }
}
