package com.wanted.onboarding.apis.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ModifyRecruitmentRequest(
    @JsonProperty(value = "recruitment_id") Long recruitmentId, String position, Integer reward, String description, String skill
) {}
