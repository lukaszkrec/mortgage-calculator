package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mortgage.model.InputData;
import org.mortgage.model.MortgageReference;
import org.mortgage.model.MortgageResidual;
import org.mortgage.model.MortgageType;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;
import org.mortgage.model.RateAmounts;
import org.mortgage.model.TimePoint;

class DecreasingAmountCalculationServiceImplTest {
    /**
     * Method under test: {@link DecreasingAmountCalculationServiceImpl#calculate(InputData, Overpayment)}
     */
    @Test
    void testCalculate() {
        DecreasingAmountCalculationServiceImpl decreasingAmountCalculationServiceImpl = new DecreasingAmountCalculationServiceImpl();
        InputData inputData = new InputData();
        BigDecimal amount = BigDecimal.valueOf(42L);
        Overpayment overpayment = new Overpayment(amount, BigDecimal.valueOf(42L));

        RateAmounts actualCalculateResult = decreasingAmountCalculationServiceImpl.calculate(inputData, overpayment);
        Overpayment overpayment2 = actualCalculateResult.getOverpayment();
        assertSame(overpayment, overpayment2);
        assertEquals("830.37", actualCalculateResult.getInterestAmount().toString());
        assertEquals("1677.52", actualCalculateResult.getCapitalAmount().toString());
        assertEquals("2507.89", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment2.getAmount().toString());
        assertEquals("42", overpayment2.getProvisionAmount().toString());
    }

    /**
     * Method under test: {@link DecreasingAmountCalculationServiceImpl#calculate(InputData, Overpayment)}
     */
    @Test
    void testCalculate3() {
        DecreasingAmountCalculationServiceImpl decreasingAmountCalculationServiceImpl = new DecreasingAmountCalculationServiceImpl();
        LocalDate repaymentStartDate = LocalDate.of(1970, 1, 1);
        BigDecimal wiborPercent = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(-1L);
        BigDecimal monthsDuration = BigDecimal.valueOf(42L);
        BigDecimal marginPercent = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(42L);
        BigDecimal overpaymentStartMonth = BigDecimal.valueOf(42L);
        InputData inputData = new InputData(repaymentStartDate, wiborPercent, amount, monthsDuration,
                MortgageType.CONSTANT, marginPercent, overpaymentProvisionPercent, overpaymentProvisionMonths,
                overpaymentStartMonth, new HashMap<>(), "Overpayment Reduce Way", true, 10);

        BigDecimal amount2 = BigDecimal.valueOf(42L);
        Overpayment overpayment = new Overpayment(amount2, BigDecimal.valueOf(42L));

        RateAmounts actualCalculateResult = decreasingAmountCalculationServiceImpl.calculate(inputData, overpayment);
        BigDecimal capitalAmount = actualCalculateResult.getCapitalAmount();
        assertSame(amount, capitalAmount);
        Overpayment overpayment2 = actualCalculateResult.getOverpayment();
        assertSame(overpayment, overpayment2);
        assertEquals("-0.07", actualCalculateResult.getInterestAmount().toString());
        assertEquals("-1", capitalAmount.toString());
        assertEquals("-1.07", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment2.getAmount().toString());
        assertEquals("42", overpayment2.getProvisionAmount().toString());
    }


    /**
     * Method under test: {@link DecreasingAmountCalculationServiceImpl#calculate(InputData, Overpayment, Rate)}
     */
    @Test
    void testCalculate6() {
        DecreasingAmountCalculationServiceImpl decreasingAmountCalculationServiceImpl = new DecreasingAmountCalculationServiceImpl();
        InputData inputData = new InputData();
        BigDecimal amount = BigDecimal.valueOf(42L);
        Overpayment overpayment = new Overpayment(amount, BigDecimal.valueOf(42L));

        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        Overpayment overpayment2 = new Overpayment(amount2, BigDecimal.valueOf(42L));

        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment2);

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        RateAmounts actualCalculateResult = decreasingAmountCalculationServiceImpl.calculate(inputData, overpayment,
                new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                        new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        Overpayment overpayment3 = actualCalculateResult.getOverpayment();
        assertEquals(overpayment2, overpayment3);
        assertEquals("0.12", actualCalculateResult.getInterestAmount().toString());
        assertEquals("1.00", actualCalculateResult.getCapitalAmount().toString());
        assertEquals("1.12", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment3.getAmount().toString());
        assertEquals("42", overpayment3.getProvisionAmount().toString());
    }


    /**
     * Method under test: {@link DecreasingAmountCalculationServiceImpl#calculate(InputData, Overpayment, Rate)}
     */
    @Test
    void testCalculate8() {
        DecreasingAmountCalculationServiceImpl decreasingAmountCalculationServiceImpl = new DecreasingAmountCalculationServiceImpl();
        InputData inputData = new InputData();
        BigDecimal amount = BigDecimal.valueOf(42L);
        Overpayment overpayment = new Overpayment(amount, BigDecimal.valueOf(42L));

        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        Overpayment overpayment2 = new Overpayment(amount2, BigDecimal.valueOf(42L));

        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment2);

        BigDecimal residualAmount = BigDecimal.valueOf(-1L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        RateAmounts actualCalculateResult = decreasingAmountCalculationServiceImpl.calculate(inputData, overpayment,
                new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                        new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        BigDecimal capitalAmount2 = actualCalculateResult.getCapitalAmount();
        assertSame(residualAmount, capitalAmount2);
        Overpayment overpayment3 = actualCalculateResult.getOverpayment();
        assertEquals(overpayment2, overpayment3);
        assertEquals("0.00", actualCalculateResult.getInterestAmount().toString());
        assertEquals("-1", capitalAmount2.toString());
        assertEquals("-1.00", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment3.getAmount().toString());
        assertEquals("42", overpayment3.getProvisionAmount().toString());
    }
}

