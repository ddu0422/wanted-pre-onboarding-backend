package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.exception.RecruitmentNotFoundException;
import com.wanted.onboarding.usecase.query.GetRecruitmentQuery;
import com.wanted.onboarding.usecase.result.GetRecruitmentResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRecruitmentTest {

    @InjectMocks
    private GetRecruitment getRecruitment;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private GetRecruitmentQuery query;

    @Mock
    private Recruitment recruitment;

    @Mock
    private Recruitment otherRecruitment;

    @Mock
    private Company company;

    @Nested
    @DisplayName("채용공고가 존재하지 않는 경우")
    class NotFoundRecruitment {

        @Test
        @DisplayName("채용공고를 가져올 수 없다")
        void test() {
            when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThatThrownBy(() -> getRecruitment.execute(query))
                .isInstanceOf(RecruitmentNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("채용공고가 존재하는 경우")
    class FoundRecruitment {

        @Test
        @DisplayName("채용공고 상세정보를 가져올 수 있다")
        void test() {
            List<Recruitment> otherRecruitments = List.of(otherRecruitment);

            when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.of(recruitment));
            when(recruitment.getCompany()).thenReturn(company);
            when(recruitment.getReward()).thenReturn(BigDecimal.ONE);
            when(recruitmentRepository.findByCompany(company)).thenReturn(otherRecruitments);
            when(otherRecruitment.isNotSame(recruitment)).thenReturn(true);

            GetRecruitmentResult result = getRecruitment.execute(query);

            assertEquals(1, result.otherRecruitmentIds().size());
        }
    }
}
