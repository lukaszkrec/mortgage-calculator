package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mortgage.model.InputData;
import org.mortgage.model.MortgageReference;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;

class RateCalculationServiceImplTest {
    /**
     * Method under test: {@link RateCalculationServiceImpl#calculate(InputData)}
     */
    @Test
    void testCalculate() {
        OverpaymentCalculationService overpaymentCalculationService = mock(OverpaymentCalculationService.class);
        BigDecimal amount = BigDecimal.valueOf(42L);
        BigDecimal provisionAmount = BigDecimal.valueOf(42L);
        when(overpaymentCalculationService.calculate(Mockito.<BigDecimal>any(), Mockito.<InputData>any()))
                .thenReturn(new Overpayment(amount, provisionAmount));
        TimePointServiceImpl timePointCalculationService = new TimePointServiceImpl();
        ConstantAmountCalculationServiceImpl constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
        AmountCalculationServiceImpl amountsCalculationService = new AmountCalculationServiceImpl(
                constantAmountsCalculationService, new DecreasingAmountCalculationServiceImpl());

        ResidualCalculateServiceImpl residualCalculationService = new ResidualCalculateServiceImpl();
        RateCalculationServiceImpl rateCalculationServiceImpl = new RateCalculationServiceImpl(timePointCalculationService,
                amountsCalculationService, residualCalculationService, new ReferenceCalculationServiceImpl(),
                overpaymentCalculationService);
        List<Rate> actualCalculateResult = rateCalculationServiceImpl.calculate(new InputData());
        assertEquals(176, actualCalculateResult.size());
        MortgageReference expectedMortgageReference = actualCalculateResult.get(2).getMortgageReference();
        MortgageReference mortgageReference = actualCalculateResult.get(174).getMortgageReference();
        assertEquals(expectedMortgageReference, mortgageReference);
        MortgageReference mortgageReference2 = actualCalculateResult.get(173).getMortgageReference();
        assertEquals(mortgageReference, mortgageReference2);
        MortgageReference mortgageReference3 = actualCalculateResult.get(4).getMortgageReference();
        assertEquals(mortgageReference2, mortgageReference3);
        MortgageReference mortgageReference4 = actualCalculateResult.get(172).getMortgageReference();
        assertEquals(mortgageReference3, mortgageReference4);
        MortgageReference mortgageReference5 = actualCalculateResult.get(171).getMortgageReference();
        assertEquals(mortgageReference4, mortgageReference5);
        MortgageReference mortgageReference6 = actualCalculateResult.get(3).getMortgageReference();
        assertEquals(mortgageReference5, mortgageReference6);
        MortgageReference mortgageReference7 = actualCalculateResult.get(1).getMortgageReference();
        assertEquals(mortgageReference6, mortgageReference7);
        MortgageReference mortgageReference8 = actualCalculateResult.get(175).getMortgageReference();
        assertEquals(mortgageReference7, mortgageReference8);
        BigDecimal expectedRateNumber = provisionAmount.ONE;
        Rate getResult = actualCalculateResult.get(0);
        assertSame(expectedRateNumber, getResult.getRateNumber());
        assertEquals(mortgageReference8, getResult.getMortgageReference());
        verify(overpaymentCalculationService, atLeast(1)).calculate(Mockito.<BigDecimal>any(), Mockito.<InputData>any());
    }

    /**
     * Method under test: {@link RateCalculationServiceImpl#calculate(InputData)}
     */
    @Test
    void testCalculate6() {
        OverpaymentCalculationService overpaymentCalculationService = mock(OverpaymentCalculationService.class);
        BigDecimal amount = BigDecimal.valueOf(-1L);
        BigDecimal provisionAmount = BigDecimal.valueOf(42L);
        when(overpaymentCalculationService.calculate(Mockito.<BigDecimal>any(), Mockito.<InputData>any()))
                .thenReturn(new Overpayment(amount, provisionAmount));
        TimePointServiceImpl timePointCalculationService = new TimePointServiceImpl();
        ConstantAmountCalculationServiceImpl constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
        AmountCalculationServiceImpl amountsCalculationService = new AmountCalculationServiceImpl(
                constantAmountsCalculationService, new DecreasingAmountCalculationServiceImpl());

        ResidualCalculateServiceImpl residualCalculationService = new ResidualCalculateServiceImpl();
        RateCalculationServiceImpl rateCalculationServiceImpl = new RateCalculationServiceImpl(
                timePointCalculationService, amountsCalculationService, residualCalculationService,
                new ReferenceCalculationServiceImpl(), overpaymentCalculationService);
        List<Rate> actualCalculateResult = rateCalculationServiceImpl.calculate(new InputData());
        assertEquals(180, actualCalculateResult.size());
        MortgageReference expectedMortgageReference = actualCalculateResult.get(2).getMortgageReference();
        MortgageReference mortgageReference = actualCalculateResult.get(178).getMortgageReference();
        assertEquals(expectedMortgageReference, mortgageReference);
        MortgageReference mortgageReference2 = actualCalculateResult.get(177).getMortgageReference();
        assertEquals(mortgageReference, mortgageReference2);
        MortgageReference mortgageReference3 = actualCalculateResult.get(4).getMortgageReference();
        assertEquals(mortgageReference2, mortgageReference3);
        MortgageReference mortgageReference4 = actualCalculateResult.get(176).getMortgageReference();
        assertEquals(mortgageReference3, mortgageReference4);
        MortgageReference mortgageReference5 = actualCalculateResult.get(175).getMortgageReference();
        assertEquals(mortgageReference4, mortgageReference5);
        MortgageReference mortgageReference6 = actualCalculateResult.get(3).getMortgageReference();
        assertEquals(mortgageReference5, mortgageReference6);
        MortgageReference mortgageReference7 = actualCalculateResult.get(1).getMortgageReference();
        assertEquals(mortgageReference6, mortgageReference7);
        MortgageReference mortgageReference8 = actualCalculateResult.get(179).getMortgageReference();
        assertEquals(mortgageReference7, mortgageReference8);
        BigDecimal expectedRateNumber = provisionAmount.ONE;
        Rate getResult = actualCalculateResult.get(0);
        assertSame(expectedRateNumber, getResult.getRateNumber());
        assertEquals(mortgageReference8, getResult.getMortgageReference());
        verify(overpaymentCalculationService, atLeast(1)).calculate(Mockito.<BigDecimal>any(), Mockito.<InputData>any());
    }
}

