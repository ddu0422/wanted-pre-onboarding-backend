package com.wanted.onboarding.apis;

import com.wanted.onboarding.apis.request.ApplyRecruitmentRequest;
import com.wanted.onboarding.usecase.ApplyRecruitment;
import com.wanted.onboarding.usecase.command.ApplyRecruitmentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant/v1")
public class ApplicantApis {

    private final ApplyRecruitment applyRecruitment;

    @PostMapping
    public ResponseEntity<Void> applyRecruitment(@RequestBody ApplyRecruitmentRequest request) {
        applyRecruitment.execute(ApplyRecruitmentCommand.builder()
            .memberId(request.memberId())
            .recruitmentId(request.recruitmentId())
            .build());

        return ResponseEntity.ok()
            .build();
    }
}
