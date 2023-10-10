package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.model.exception.DoNotMatchCompanyException;
import com.wanted.onboarding.repository.CompanyRepository;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.command.RemoveRecruitmentCommand;
import com.wanted.onboarding.usecase.exception.CompanyNotFoundException;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveRecruitmentTest {

    @InjectMocks
    private RemoveRecruitment removeRecruitment;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private RemoveRecruitmentCommand command;

    @Mock
    private Company company;

    @Mock
    private Recruitment recruitment;

    @Nested
    @DisplayName("삭제를 요청한 회사가 존재하지 않는 경우")
    class NotFoundCompany {

        @Test
        @DisplayName("채용공고를 삭제할 수 없다")
        void test() {
            when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> removeRecruitment.execute(command))
                .isInstanceOf(CompanyNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("삭제를 요청한 회사가 존재하고")
    class FoundCompany {

        @BeforeEach
        void setUp() {
            when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        }

        @Nested
        @DisplayName("채용공고가 없는 경우")
        class NotFoundRecruitment {

            @Test
            @DisplayName("채용공고를 삭제할 수 없다")
            void test() {
                when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.empty());

                assertThatThrownBy(() -> removeRecruitment.execute(command))
                    .isInstanceOf(RecruitmentNotFoundException.class);
            }
        }


        @Nested
        @DisplayName("다른 회사의 채용공고인 경우")
        class AnotherRecruitment {

            @Test
            @DisplayName("채용공고를 삭제할 수 없다")
            void test() {
                when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.of(recruitment));
                doThrow(DoNotMatchCompanyException.class).when(recruitment).isSame(company);

                assertThatThrownBy(() -> removeRecruitment.execute(command))
                    .isInstanceOf(DoNotMatchCompanyException.class);

                verify(recruitmentRepository, times(0)).deleteById(anyLong());
            }
        }

        @Nested
        @DisplayName("현재 회사의 채용공고인 경우")
        class CurrentRecruitment {

            @Test
            @DisplayName("채용공고를 삭제할 수 있다")
            void test() {
                when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.of(recruitment));
                doNothing().when(recruitment).isSame(company);

                removeRecruitment.execute(command);

                verify(recruitmentRepository, times(1)).deleteById(anyLong());
            }
        }
    }

}
