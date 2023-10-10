package com.wanted.onboarding.apis;

import com.wanted.onboarding.apis.request.EnrollRecruitmentRequest;
import com.wanted.onboarding.apis.request.ModifyRecruitmentRequest;
import com.wanted.onboarding.apis.response.FindAllRecruitmentResponse;
import com.wanted.onboarding.apis.response.RecruitmentResponse;
import com.wanted.onboarding.apis.response.SearchRecruitmentResponse;
import com.wanted.onboarding.usecase.EnrollRecruitment;
import com.wanted.onboarding.usecase.FindAllRecruitment;
import com.wanted.onboarding.usecase.ModifyRecruitment;
import com.wanted.onboarding.usecase.RemoveRecruitment;
import com.wanted.onboarding.usecase.SearchRecruitment;
import com.wanted.onboarding.usecase.command.EnrollRecruitmentCommand;
import com.wanted.onboarding.usecase.command.ModifyRecruitmentCommand;
import com.wanted.onboarding.usecase.command.RemoveRecruitmentCommand;
import com.wanted.onboarding.usecase.query.SearchRecruitmentQuery;
import com.wanted.onboarding.usecase.result.FindAllRecruitmentResult;
import com.wanted.onboarding.usecase.result.RecruitmentResult;
import com.wanted.onboarding.usecase.result.SearchRecruitmentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment/v1")
public class RecruitmentApis {

    private final EnrollRecruitment enrollRecruitment;
    private final ModifyRecruitment modifyRecruitment;
    private final RemoveRecruitment removeRecruitment;
    private final FindAllRecruitment findAllRecruitment;
    private final SearchRecruitment searchRecruitment;

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

    @GetMapping
    public ResponseEntity<FindAllRecruitmentResponse> findAllRecruitment() {
        FindAllRecruitmentResult result = findAllRecruitment.execute();

        return ResponseEntity.ok(FindAllRecruitmentResponse.builder()
            .recruitmentResponses(result.recruitmentResults().stream()
                .map(this::changeRecruitmentResponse)
                .toList())
            .build());
    }

    private RecruitmentResponse changeRecruitmentResponse(RecruitmentResult result) {
        return RecruitmentResponse.builder()
            .recruitmentId(result.recruitmentId())
            .companyName(result.companyName())
            .country(result.country())
            .region(result.region())
            .position(result.position())
            .reward(result.reward())
            .skill(result.skill())
            .build();
    }

    @GetMapping("/search")
    public SearchRecruitmentResponse searchRecruitment(String keyword) {
        SearchRecruitmentResult result = searchRecruitment.execute(SearchRecruitmentQuery.builder()
            .keyword(keyword)
            .build());

        return SearchRecruitmentResponse.builder()
            .recruitmentResponses(result.recruitmentResults().stream()
                .map(this::changeRecruitmentResponse)
                .toList())
            .build();
    }
}
