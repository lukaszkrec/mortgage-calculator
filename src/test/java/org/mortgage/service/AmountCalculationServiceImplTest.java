package org.mortgage.service;

import org.junit.jupiter.api.Test;
import org.mortgage.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AmountCalculationServiceImplTest {
    /**
     * Method under test: {@link AmountCalculationServiceImpl#calculate(InputData, Overpayment)}
     */
    @Test
    void testCalculate() {
        ConstantAmountCalculationServiceImpl constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
        AmountCalculationServiceImpl amountCalculationServiceImpl = new AmountCalculationServiceImpl(
                constantAmountsCalculationService, new DecreasingAmountCalculationServiceImpl());
        InputData inputData = new InputData();
        BigDecimal amount = BigDecimal.valueOf(42L);
        Overpayment overpayment = new Overpayment(amount, BigDecimal.valueOf(42L));

        RateAmounts actualCalculateResult = amountCalculationServiceImpl.calculate(inputData, overpayment);
        Overpayment overpayment2 = actualCalculateResult.getOverpayment();
        assertSame(overpayment, overpayment2);
        assertEquals("830.37", actualCalculateResult.getInterestAmount().toString());
        assertEquals("1298.71", actualCalculateResult.getCapitalAmount().toString());
        assertEquals("2129.08", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment2.getAmount().toString());
        assertEquals("42", overpayment2.getProvisionAmount().toString());
    }

    /**
     * Method under test: {@link AmountCalculationServiceImpl#calculate(InputData, Overpayment)}
     */
    @Test
    void testCalculate2() {
        ConstantAmountCalculationServiceImpl constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
        AmountCalculationServiceImpl amountCalculationServiceImpl = new AmountCalculationServiceImpl(
                constantAmountsCalculationService, new DecreasingAmountCalculationServiceImpl());

        InputData inputData = new InputData();
        inputData.withType(MortgageType.DECREASING);
        BigDecimal amount = BigDecimal.valueOf(42L);
        Overpayment overpayment = new Overpayment(amount, BigDecimal.valueOf(42L));

        RateAmounts actualCalculateResult = amountCalculationServiceImpl.calculate(inputData, overpayment);
        Overpayment overpayment2 = actualCalculateResult.getOverpayment();
        assertSame(overpayment, overpayment2);
        assertEquals("830.37", actualCalculateResult.getInterestAmount().toString());
        assertEquals("1677.52", actualCalculateResult.getCapitalAmount().toString());
        assertEquals("2507.89", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment2.getAmount().toString());
        assertEquals("42", overpayment2.getProvisionAmount().toString());
    }


    /**
     * Method under test: {@link AmountCalculationServiceImpl#calculate(InputData, Overpayment, Rate)}
     */
    @Test
    void testCalculate6() {
        ConstantAmountCalculationServiceImpl constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
        AmountCalculationServiceImpl amountCalculationServiceImpl = new AmountCalculationServiceImpl(
                constantAmountsCalculationService, new DecreasingAmountCalculationServiceImpl());
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
        RateAmounts actualCalculateResult = amountCalculationServiceImpl.calculate(inputData, overpayment,
                new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                        new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        Overpayment overpayment3 = actualCalculateResult.getOverpayment();
        assertEquals(overpayment2, overpayment3);
        assertEquals("0.12", actualCalculateResult.getInterestAmount().toString());
        assertEquals("0.94", actualCalculateResult.getCapitalAmount().toString());
        assertEquals("1.06", actualCalculateResult.getRateAmount().toString());
        assertEquals("42", overpayment3.getAmount().toString());
        assertEquals("42", overpayment3.getProvisionAmount().toString());
    }

    /**
     * Method under test: {@link AmountCalculationServiceImpl#calculate(InputData, Overpayment, Rate)}
     */
    @Test
    void testCalculate7() {
        ConstantAmountCalculationServiceImpl constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
        AmountCalculationServiceImpl amountCalculationServiceImpl = new AmountCalculationServiceImpl(
                constantAmountsCalculationService, new DecreasingAmountCalculationServiceImpl());

        InputData inputData = new InputData();
        inputData.withType(MortgageType.DECREASING);
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
        RateAmounts actualCalculateResult = amountCalculationServiceImpl.calculate(inputData, overpayment,
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
}

