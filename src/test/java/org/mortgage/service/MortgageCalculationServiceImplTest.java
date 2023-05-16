package org.mortgage.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mortgage.model.InputData;
import org.mortgage.model.MortgageReference;
import org.mortgage.model.MortgageResidual;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;
import org.mortgage.model.RateAmounts;
import org.mortgage.model.Summary;
import org.mortgage.model.TimePoint;

class MortgageCalculationServiceImplTest {
    /**
     * Method under test: {@link MortgageCalculationServiceImpl#calculate(InputData)}
     */
    @Test
    void testCalculate() {
        RateCalculationService rateCalculationService = mock(RateCalculationService.class);
        when(rateCalculationService.calculate(Mockito.<InputData>any())).thenReturn(new ArrayList<>());
        SummaryService summaryService = mock(SummaryService.class);
        BigDecimal interestSum = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionSum = BigDecimal.valueOf(42L);
        BigDecimal totalLostSum = BigDecimal.valueOf(42L);
        when(summaryService.calculateSummary(Mockito.<List<Rate>>any()))
                .thenReturn(new Summary(interestSum, overpaymentProvisionSum, totalLostSum, BigDecimal.valueOf(42L)));
        MortgageCalculationServiceImpl mortgageCalculationServiceImpl = new MortgageCalculationServiceImpl(
                rateCalculationService, new PrintingServiceImpl(), summaryService);
        mortgageCalculationServiceImpl.calculate(new InputData());
        verify(rateCalculationService).calculate(Mockito.<InputData>any());
        verify(summaryService).calculateSummary(Mockito.<List<Rate>>any());
    }
}

