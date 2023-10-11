package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Member;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.ApplicantRepository;
import com.wanted.onboarding.repository.MemberRepository;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.exception.AlreadyAppliedForRecruitmentException;
import com.wanted.onboarding.usecase.exception.MemberNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyRecruitmentTest {

    @InjectMocks
    private ApplyRecruitment applyRecruitment;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private Member member;

    @Mock
    private Recruitment recruitment;

    @Nested
    @DisplayName("사용자가 존재하지 않는 경우")
    class NotFoundMember {

        @Test
        @DisplayName("채용공고에 지원할 수 없다")
        void test() {
            when(memberRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> applyRecruitment.execute())
                .isInstanceOf(MemberNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("채용공고가 존재하지 않는 경우")
    class NotFoundRecruitment {

        @Test
        @DisplayName("채용공고에 지원할 수 없다")
        void test() {
            when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
            when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> applyRecruitment.execute())
                .isInstanceOf(RecruitmentNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("이미 지원한 채용공고인 경우")
    class AlreadyAppliedForRecruitment {

        @Test
        @DisplayName("채용공고에 지원할 수 없다")
        void test() {
            when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
            when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.of(recruitment));
            when(applicantRepository.existsByMemberAndRecruitment(member, recruitment)).thenReturn(true);

            assertThatThrownBy(() -> applyRecruitment.execute())
                .isInstanceOf(AlreadyAppliedForRecruitmentException.class);
        }
    }

    @Nested
    @DisplayName("채용공고에 지원한 적이 없는 경우")
    class NotAppliedForRecruitment {

        @Test
        @DisplayName("채용공고에 지원할 수 있다")
        void test() {
            when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
            when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.of(recruitment));
            when(applicantRepository.existsByMemberAndRecruitment(member, recruitment)).thenReturn(false);

            applyRecruitment.execute();

            verify(applicantRepository).save(any());
        }
    }
}
