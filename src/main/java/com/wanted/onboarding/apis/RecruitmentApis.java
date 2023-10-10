package com.wanted.onboarding.apis;

import com.wanted.onboarding.apis.request.EnrollRecruitmentRequest;
import com.wanted.onboarding.apis.request.ModifyRecruitmentRequest;
import com.wanted.onboarding.usecase.EnrollRecruitment;
import com.wanted.onboarding.usecase.ModifyRecruitment;
import com.wanted.onboarding.usecase.RemoveRecruitment;
import com.wanted.onboarding.usecase.command.EnrollRecruitmentCommand;
import com.wanted.onboarding.usecase.command.ModifyRecruitmentCommand;
import com.wanted.onboarding.usecase.command.RemoveRecruitmentCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruitment/v1")
record RecruitmentApis(EnrollRecruitment enrollRecruitment, ModifyRecruitment modifyRecruitment, RemoveRecruitment removeRecruitment) {

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

    @PutMapping("/{recruitmentId}")
    public ResponseEntity<Void> modifyRecruitment(@PathVariable Long recruitmentId, @RequestBody ModifyRecruitmentRequest request) {
        modifyRecruitment.execute(ModifyRecruitmentCommand.builder()
            .recruitmentId(recruitmentId)
            .position(request.position())
            .reward(request.reward())
            .description(request.description())
            .skill(request.skill())
            .build());

        return ResponseEntity.ok()
            .build();
    }

    @DeleteMapping("/{recruitmentId}")
    public ResponseEntity<Void> removeRecruitment(@PathVariable Long recruitmentId, @RequestHeader(value = "companyId") Long companyId) {
        removeRecruitment.execute(RemoveRecruitmentCommand.builder()
            .recruitmentId(recruitmentId)
            .companyId(companyId)
            .build());

        return ResponseEntity.ok()
            .build();
    }
}
