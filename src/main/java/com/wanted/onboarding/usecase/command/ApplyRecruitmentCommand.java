package com.wanted.onboarding.usecase.command;

import lombok.Builder;

@Builder
public record ApplyRecruitmentCommand(
    Long memberId, Long recruitmentId
) {}
