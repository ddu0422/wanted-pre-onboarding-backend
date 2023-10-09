package com.wanted.onboarding.usecase.command;

import lombok.Builder;

@Builder
public record ModifyRecruitmentCommand(
    Long recruitmentId, String position, Integer reward, String description, String skill
) {}
