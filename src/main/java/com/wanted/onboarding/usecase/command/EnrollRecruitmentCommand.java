package com.wanted.onboarding.usecase.command;

import lombok.Builder;

@Builder
public record EnrollRecruitmentCommand (
    Long companyId, String position, Integer reward, String description, String skill
) {}
