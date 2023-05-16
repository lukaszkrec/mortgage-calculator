package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.time.LocalDate;

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

class ResidualCalculateServiceImplTest {
    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData)}
     */
    @Test
    void testCalculate2() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        inputData.withType(MortgageType.DECREASING);
        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, inputData);
        assertEquals("301869.46", actualCalculateResult.getResidualAmount().toString());
        assertEquals("7188", actualCalculateResult.getResidualDuration().toString());
    }

    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData)}
     */
    @Test
    void testCalculate3() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(-1L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, new InputData());
        assertEquals("301869.46", actualCalculateResult.getResidualAmount().toString());
        assertEquals("-2448", actualCalculateResult.getResidualDuration().toString());
    }


    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData)}
     */
    @Test
    void testCalculate6() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(0L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, new InputData());
        assertEquals("301911.46", actualCalculateResult.getResidualAmount().toString());
        assertEquals("179", actualCalculateResult.getResidualDuration().toString());
    }

    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate10() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
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
        BigDecimal referenceDuration = BigDecimal.valueOf(42L);
        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, referenceDuration)));
        BigDecimal expectedResidualAmount = referenceDuration.ZERO;
        BigDecimal residualAmount2 = actualCalculateResult.getResidualAmount();
        assertSame(expectedResidualAmount, residualAmount2);
        assertSame(residualAmount2, actualCalculateResult.getResidualDuration());
        assertEquals("0", residualAmount2.toString());
    }

    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate11() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        InputData inputData = new InputData();
        inputData.withType(MortgageType.DECREASING);
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
        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, referenceDuration)));
        BigDecimal expectedResidualAmount = referenceDuration.ZERO;
        BigDecimal residualAmount2 = actualCalculateResult.getResidualAmount();
        assertSame(expectedResidualAmount, residualAmount2);
        assertSame(residualAmount2, actualCalculateResult.getResidualDuration());
        assertEquals("0", residualAmount2.toString());
    }

    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate16() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(0L);
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
        BigDecimal referenceDuration = BigDecimal.valueOf(42L);
        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, referenceDuration)));
        BigDecimal expectedResidualAmount = referenceDuration.ZERO;
        BigDecimal residualAmount2 = actualCalculateResult.getResidualAmount();
        assertSame(expectedResidualAmount, residualAmount2);
        assertEquals("0", residualAmount2.toString());
        assertEquals("41", actualCalculateResult.getResidualDuration().toString());
    }


    /**
     * Method under test: {@link ResidualCalculateServiceImpl#calculate(RateAmounts, InputData, Rate)}
     */
    @Test
    void testCalculate21() {
        ResidualCalculateServiceImpl residualCalculateServiceImpl = new ResidualCalculateServiceImpl();
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

        BigDecimal residualAmount = BigDecimal.valueOf(0L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        BigDecimal referenceDuration = BigDecimal.valueOf(42L);
        MortgageResidual actualCalculateResult = residualCalculateServiceImpl.calculate(rateAmounts, inputData,
                new Rate(rateNumber, timePoint, rateAmounts2, mortgageResidual,
                        new MortgageReference(referenceAmount, referenceDuration)));
        BigDecimal expectedResidualAmount = referenceDuration.ZERO;
        BigDecimal residualAmount2 = actualCalculateResult.getResidualAmount();
        assertSame(expectedResidualAmount, residualAmount2);
        assertSame(residualAmount2, actualCalculateResult.getResidualDuration());
        assertEquals("0", residualAmount2.toString());
    }
}

