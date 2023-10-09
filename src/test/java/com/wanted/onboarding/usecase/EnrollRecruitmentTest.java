package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.repository.CompanyRepository;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.command.EnrollRecruitmentCommand;
import com.wanted.onboarding.usecase.exception.CompanyNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnrollRecruitmentTest {

    @InjectMocks
    private EnrollRecruitment enrollRecruitment;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private EnrollRecruitmentCommand command;

    @Nested
    @DisplayName("등록을 요청한 회사가 존재하지 않는 경우")
    class NotFoundCompany {

        @Test
        @DisplayName("채용공고를 등록할 수 없다")
        void test1() {
            when(companyRepository.findById(command.companyId())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> enrollRecruitment.execute(command)).isInstanceOf(CompanyNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("등록을 요청한 회사가 존재하는 경우")
    class FoundCompany {

        @Test
        @DisplayName("채용공고를 등록할 수 있다.")
        void test1() {
            when(companyRepository.findById(command.companyId())).thenReturn(Optional.of(mock(Company.class)));

            assertDoesNotThrow(() -> enrollRecruitment.execute(command));

            verify(recruitmentRepository).save(any());
        }
    }
}
