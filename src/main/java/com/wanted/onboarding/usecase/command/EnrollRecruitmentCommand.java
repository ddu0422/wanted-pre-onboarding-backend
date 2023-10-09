package com.wanted.onboarding.usecase.command;

public record EnrollRecruitmentCommand (
    Long companyId, String position, int reward, String description, String skill
) {}
