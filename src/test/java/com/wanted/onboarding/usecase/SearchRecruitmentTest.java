package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.query.SearchRecruitmentQuery;
import com.wanted.onboarding.usecase.result.SearchRecruitmentResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchRecruitmentTest {

    @InjectMocks
    private SearchRecruitment searchRecruitment;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private SearchRecruitmentQuery query;

    @Mock
    private Recruitment recruitment1;

    @Mock
    private Company company;

    @Nested
    @DisplayName("채용포지션에 검색 결과가 포함된 경우")
    class SearchPosition {

        @Test
        void test() {
            List<Recruitment> recruitments = List.of(recruitment1);

            when(query.keyword()).thenReturn("position");
            when(recruitmentRepository.findAll(any(Specification.class))).thenReturn(recruitments);
            when(recruitment1.getPosition()).thenReturn("position");
            when(recruitment1.getCompany()).thenReturn(company);
            when(recruitment1.getReward()).thenReturn(BigDecimal.ONE);

            SearchRecruitmentResult result = searchRecruitment.execute(query);

            assertEquals(query.keyword(), result.recruitmentResults().get(0).position());
        }
    }

    @Nested
    @DisplayName("회사 이름에 검색 결과가 포함된 경우")
    class SearchCompanyName {

        @Test
        void test() {
            List<Recruitment> recruitments = List.of(recruitment1);

            when(query.keyword()).thenReturn("company name");
            when(recruitmentRepository.findAll(any(Specification.class))).thenReturn(recruitments);
            when(recruitment1.getCompany()).thenReturn(company);
            when(recruitment1.getCompany().getName()).thenReturn("company name");
            when(recruitment1.getReward()).thenReturn(BigDecimal.ONE);

            SearchRecruitmentResult result = searchRecruitment.execute(query);

            assertEquals(query.keyword(), result.recruitmentResults().get(0).companyName());
        }
    }

    @Nested
    @DisplayName("사용 기술에 검색 결과가 포함된 경우")
    class SearchSkill {

        @Test
        void test() {
            List<Recruitment> recruitments = List.of(recruitment1);

            when(query.keyword()).thenReturn("skill");
            when(recruitmentRepository.findAll(any(Specification.class))).thenReturn(recruitments);
            when(recruitment1.getSkill()).thenReturn("skill");
            when(recruitment1.getCompany()).thenReturn(company);
            when(recruitment1.getReward()).thenReturn(BigDecimal.ONE);

            SearchRecruitmentResult result = searchRecruitment.execute(query);

            assertEquals(query.keyword(), result.recruitmentResults().get(0).skill());
        }
    }
}
