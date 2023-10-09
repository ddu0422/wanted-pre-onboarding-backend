package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.command.ModifyRecruitmentCommand;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyRecruitment {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void execute(ModifyRecruitmentCommand command) {
        Recruitment recruitment = recruitmentRepository.findById(command.recruitmentId())
            .orElseThrow(RecruitmentNotFoundException::new);

        recruitment.change(command.position(), command.reward(), command.description(), command.skill());
    }
}
