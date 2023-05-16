package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mortgage.model.InputData;
import org.mortgage.model.MortgageReference;
import org.mortgage.model.MortgageResidual;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;
import org.mortgage.model.RateAmounts;
import org.mortgage.model.TimePoint;

class ReferenceCalculationServiceImplTest {
    /**
     * Method under test: {@link ReferenceCalculationServiceImpl#calculate(RateAmounts, InputData)}
     */
    @Test
    void testCalculate() {
        ReferenceCalculationServiceImpl referenceCalculationServiceImpl = new ReferenceCalculationServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        MortgageReference actualCalculateResult = referenceCalculationServiceImpl.calculate(rateAmounts, new InputData());
        assertEquals("301953.46", actualCalculateResult.getReferenceAmount().toString());
        assertEquals("180", actualCalculateResult.getReferenceDuration().toString());
    }

    /**
     * Method under test: {@link ReferenceCalculationServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate3() {
        ReferenceCalculationServiceImpl referenceCalculationServiceImpl = new ReferenceCalculationServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount2 = BigDecimal.valueOf(42L);
        BigDecimal interestAmount2 = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount2 = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts2 = new RateAmounts(rateAmount2, interestAmount2, capitalAmount2,
                new Overpayment(amount2, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        MortgageReference actualCalculateResult = referenceCalculationServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        assertEquals("301953.46", actualCalculateResult.getReferenceAmount().toString());
        assertEquals("180", actualCalculateResult.getReferenceDuration().toString());
    }

    /**
     * Method under test: {@link ReferenceCalculationServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate4() {
        ReferenceCalculationServiceImpl referenceCalculationServiceImpl = new ReferenceCalculationServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        inputData.withOverpaymentReduceWay((String) "REDUCE_RATE");
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount2 = BigDecimal.valueOf(42L);
        BigDecimal interestAmount2 = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount2 = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts2 = new RateAmounts(rateAmount2, interestAmount2, capitalAmount2,
                new Overpayment(amount2, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        BigDecimal referenceDuration = BigDecimal.valueOf(42L);
        MortgageReference actualCalculateResult = referenceCalculationServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, referenceDuration)));
        BigDecimal expectedReferenceAmount = referenceDuration.ZERO;
        BigDecimal referenceAmount2 = actualCalculateResult.getReferenceAmount();
        assertSame(expectedReferenceAmount, referenceAmount2);
        assertEquals("0", referenceAmount2.toString());
        assertEquals("41", actualCalculateResult.getReferenceDuration().toString());
    }


    /**
     * Method under test: {@link ReferenceCalculationServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate7() {
        ReferenceCalculationServiceImpl referenceCalculationServiceImpl = new ReferenceCalculationServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(-1L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        inputData.withOverpaymentReduceWay((String) "REDUCE_RATE");
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount2 = BigDecimal.valueOf(42L);
        BigDecimal interestAmount2 = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount2 = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts2 = new RateAmounts(rateAmount2, interestAmount2, capitalAmount2,
                new Overpayment(amount2, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        MortgageReference actualCalculateResult = referenceCalculationServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        BigDecimal referenceAmount2 = actualCalculateResult.getReferenceAmount();
        assertEquals(rateAmount, referenceAmount2);
        BigDecimal referenceDuration = actualCalculateResult.getReferenceDuration();
        assertEquals(rateAmount, referenceDuration);
        assertEquals("42", referenceAmount2.toString());
        assertEquals("42", referenceDuration.toString());
    }


    /**
     * Method under test: {@link ReferenceCalculationServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate10() {
        ReferenceCalculationServiceImpl referenceCalculationServiceImpl = new ReferenceCalculationServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        inputData.withOverpaymentReduceWay((String) "foo");
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount2 = BigDecimal.valueOf(42L);
        BigDecimal interestAmount2 = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount2 = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts2 = new RateAmounts(rateAmount2, interestAmount2, capitalAmount2,
                new Overpayment(amount2, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        assertThrows(MortgageException.class,
                () -> referenceCalculationServiceImpl.calculate(rateAmounts, inputData, new Rate(rateNumber, timePoint,
                        rateAmounts2, mortgageResidual, new MortgageReference(referenceAmount, BigDecimal.valueOf(42L)))));
    }

    /**
     * Method under test: {@link ReferenceCalculationServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate11() {
        ReferenceCalculationServiceImpl referenceCalculationServiceImpl = new ReferenceCalculationServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        inputData.withOverpaymentReduceWay((String) "REDUCE_RATE");
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount2 = BigDecimal.valueOf(42L);
        BigDecimal interestAmount2 = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount2 = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts2 = new RateAmounts(rateAmount2, interestAmount2, capitalAmount2,
                new Overpayment(amount2, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(0L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        BigDecimal referenceDuration = BigDecimal.valueOf(42L);
        MortgageReference actualCalculateResult = referenceCalculationServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, referenceDuration)));
        BigDecimal expectedReferenceAmount = referenceDuration.ZERO;
        BigDecimal referenceAmount2 = actualCalculateResult.getReferenceAmount();
        assertSame(expectedReferenceAmount, referenceAmount2);
        assertSame(referenceAmount2, actualCalculateResult.getReferenceDuration());
        assertEquals("0", referenceAmount2.toString());
    }
}

