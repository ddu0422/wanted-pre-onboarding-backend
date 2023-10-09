package com.wanted.onboarding.apis.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EnrollRecruitmentRequest(
    @JsonProperty("company_id") Long companyId, String position, int reward, String description, String skill
) {}
