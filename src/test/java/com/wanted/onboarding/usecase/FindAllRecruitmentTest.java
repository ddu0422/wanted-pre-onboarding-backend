package com.wanted.onboarding.usecase;

import com.wanted.onboarding.model.Company;
import com.wanted.onboarding.model.Recruitment;
import com.wanted.onboarding.repository.RecruitmentRepository;
import com.wanted.onboarding.usecase.result.FindAllRecruitmentResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllRecruitmentTest {

    @InjectMocks
    private FindAllRecruitment findAllRecruitment;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    private final List<Recruitment> recruitments = new ArrayList<>();

    @Mock
    private Recruitment recruitment;

    @Mock
    private Company company;

    @Test
    @DisplayName("전체 채용공고를 반환한다")
    void test() {
        recruitments.add(recruitment);
        recruitments.add(recruitment);

        when(recruitmentRepository.findAll()).thenReturn(recruitments);
        when(recruitment.getCompany()).thenReturn(company);
        when(recruitment.getReward()).thenReturn(BigDecimal.ONE);

        FindAllRecruitmentResult execute = findAllRecruitment.execute();

        assertEquals(2, execute.recruitmentResults().size());
    }
}
