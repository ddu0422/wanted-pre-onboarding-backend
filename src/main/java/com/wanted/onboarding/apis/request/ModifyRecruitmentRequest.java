package com.wanted.onboarding.apis.request;

public record ModifyRecruitmentRequest(
    String position, Integer reward, String description, String skill
) {}
