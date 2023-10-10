package com.wanted.onboarding.usecase.command;

import lombok.Builder;

@Builder
public record RemoveRecruitmentCommand(Long companyId, Long recruitmentId) {}
