package com.wanted.onboarding.usecase.exception;

public class CompanyNotFoundException extends RuntimeException {

    private static final String MESSAGE = "회사를 찾을 수 없습니다.";

    public CompanyNotFoundException() {
        super(MESSAGE);
    }
}
