package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.command.ModifyRecruitmentCommand;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModifyRecruitmentTest {

    @InjectMocks
    private ModifyRecruitment modifyRecruitment;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private ModifyRecruitmentCommand command;

    @Mock
    private Recruitment recruitment;


    @Nested
    @DisplayName("채용공고가 없는 경우")
    class NotFoundRecruitment {

        @Test
        @DisplayName("채용공고를 수정할 수 없다")
        void test1() {
            when(recruitmentRepository.findById(command.recruitmentId())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> modifyRecruitment.execute(command)).isInstanceOf(RecruitmentNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("채용공고가 있는 경우")
    class FoundRecruitment {

        @Test
        @DisplayName("채용공고를 수정할 수 있다")
        void test1() {
            when(recruitmentRepository.findById(command.recruitmentId())).thenReturn(Optional.of(recruitment));

            modifyRecruitment.execute(command);

            verify(recruitment).change(any(), any(), any(), any());
        }
    }
}
