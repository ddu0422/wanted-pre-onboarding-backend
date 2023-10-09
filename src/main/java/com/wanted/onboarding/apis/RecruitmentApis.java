package com.wanted.onboarding.apis;

import com.wanted.onboarding.apis.request.EnrollRecruitmentRequest;
import com.wanted.onboarding.usecase.EnrollRecruitment;
import com.wanted.onboarding.usecase.command.EnrollRecruitmentCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruitment/v1")
record RecruitmentApis(EnrollRecruitment enrollRecruitment) {

    @PostMapping
    public ResponseEntity<Void> enrollRecruitment(@RequestBody EnrollRecruitmentRequest request) {
        enrollRecruitment.execute(EnrollRecruitmentCommand.builder()
            .companyId(request.companyId())
            .position(request.position())
            .reward(request.reward())
            .description(request.description())
            .skill(request.skill())
            .build());

        return ResponseEntity.ok()
            .build();
    }
}
