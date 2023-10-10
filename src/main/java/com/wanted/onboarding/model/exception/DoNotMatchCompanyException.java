package com.wanted.onboarding.model.exception;

public class DoNotMatchCompanyException extends RuntimeException {

    private static final String MESSAGE = "현재 채용공고를 등록한 회사가 아닙니다.";

    public DoNotMatchCompanyException() {
        super(MESSAGE);
    }
}
