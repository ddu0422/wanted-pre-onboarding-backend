package com.wanted.onboarding.apis.request;

public record ModifyRecruitmentRequest(
    Long recruitmentId, String position, Integer reward, String description, String skill
) {}
