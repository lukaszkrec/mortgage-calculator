package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mortgage.model.InputData;
import org.mortgage.model.MortgageReference;
import org.mortgage.model.MortgageResidual;
import org.mortgage.model.MortgageType;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;
import org.mortgage.model.RateAmounts;
import org.mortgage.model.Summary;
import org.mortgage.model.TimePoint;

class PrintingServiceImplTest {
    /**
     * Method under test: {@link PrintingServiceImpl#printIntroInformation(InputData)}
     */
    @Test
    void testPrintIntroInformation2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();
        LocalDate repaymentStartDate = LocalDate.of(1970, 1, 1);
        BigDecimal wiborPercent = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        BigDecimal monthsDuration = BigDecimal.valueOf(42L);
        BigDecimal marginPercent = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(42L);
        BigDecimal overpaymentStartMonth = BigDecimal.valueOf(42L);
        printingServiceImpl.printIntroInformation(new InputData(repaymentStartDate, wiborPercent, amount, monthsDuration,
                MortgageType.CONSTANT, marginPercent, overpaymentProvisionPercent, overpaymentProvisionMonths,
                overpaymentStartMonth, new HashMap<>(), PrintingService.NEW_LINE, true, 10));
    }


    /**
     * Method under test: {@link PrintingServiceImpl#printIntroInformation(InputData)}
     */
    @Test
    void testPrintIntroInformation4() {
        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();

        InputData inputData = new InputData();
        inputData.withOverpaymentReduceWay(PrintingService.NEW_LINE);
        assertThrows(MortgageException.class, () -> printingServiceImpl.printIntroInformation(inputData));
    }


    /**
     * Method under test: {@link PrintingServiceImpl#printSchedule(List, InputData)}
     */
    @Test
    void testPrintSchedule() {
        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();
        ArrayList<Rate> rates = new ArrayList<>();
        InputData inputData = new InputData();
        printingServiceImpl.printSchedule(rates, inputData);
        assertTrue(inputData.isMortgagePrintPayoffsSchedule());
        assertEquals("REDUCE_PERIOD", inputData.getOverpaymentReduceWay());
        assertEquals(5, inputData.getOverpaymentSchema().size());
        assertEquals(1, inputData.getMortgageRateNumberToPrint().intValue());
        assertEquals(MortgageType.CONSTANT, inputData.getRateType());
        assertEquals("2020-12-10", inputData.getRepaymentStartDate().toString());
    }

    /**
     * Method under test: {@link PrintingServiceImpl#printSchedule(List, InputData)}
     */
    @Test
    void testPrintSchedule2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();

        ArrayList<Rate> rates = new ArrayList<>();
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        rates.add(new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));

        InputData inputData = new InputData();
        inputData.withMortgagePrintPayoffsSchedule(false);
        printingServiceImpl.printSchedule(rates, inputData);
    }

    /**
     * Method under test: {@link PrintingServiceImpl#printSchedule(List, InputData)}
     */
    @Test
    void testPrintSchedule3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();

        ArrayList<Rate> rates = new ArrayList<>();
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        rates.add(new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        printingServiceImpl.printSchedule(rates, new InputData());
    }


    /**
     * Method under test: {@link PrintingServiceImpl#printSchedule(List, InputData)}
     */
    @Test
    void testPrintSchedule11() {
        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();

        ArrayList<Rate> rates = new ArrayList<>();
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        BigDecimal year = BigDecimal.valueOf(42L);
        BigDecimal month = BigDecimal.valueOf(42L);
        TimePoint timePoint = new TimePoint(year, month, LocalDate.of(1970, 1, 1));

        BigDecimal rateAmount = BigDecimal.valueOf(42L);
        BigDecimal interestAmount = BigDecimal.valueOf(42L);
        BigDecimal capitalAmount = BigDecimal.valueOf(42L);
        BigDecimal amount = BigDecimal.valueOf(42L);
        RateAmounts rateAmounts = new RateAmounts(rateAmount, interestAmount, capitalAmount,
                new Overpayment(amount, BigDecimal.valueOf(42L)));

        BigDecimal residualAmount = BigDecimal.valueOf(42L);
        MortgageResidual mortgageResidual = new MortgageResidual(residualAmount, BigDecimal.valueOf(42L));

        BigDecimal referenceAmount = BigDecimal.valueOf(42L);
        rates.add(new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        LocalDate repaymentStartDate = LocalDate.of(1970, 1, 1);
        BigDecimal wiborPercent = BigDecimal.valueOf(42L);
        BigDecimal amount2 = BigDecimal.valueOf(42L);
        BigDecimal monthsDuration = BigDecimal.valueOf(42L);
        BigDecimal marginPercent = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(42L);
        BigDecimal overpaymentStartMonth = BigDecimal.valueOf(42L);
        printingServiceImpl.printSchedule(rates,
                new InputData(repaymentStartDate, wiborPercent, amount2, monthsDuration, MortgageType.CONSTANT, marginPercent,
                        overpaymentProvisionPercent, overpaymentProvisionMonths, overpaymentStartMonth, new HashMap<>(),
                        PrintingService.SCHEDULE_TABLE_FORMAT, true, 10));
    }

    /**
     * Method under test: {@link PrintingServiceImpl#printSummary(Summary)}
     */
    @Test
    void testPrintSummary() {
        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();
        BigDecimal interestSum = BigDecimal.valueOf(42L);
        BigDecimal overpaymentProvisionSum = BigDecimal.valueOf(42L);
        BigDecimal totalLostSum = BigDecimal.valueOf(42L);
        printingServiceImpl
                .printSummary(new Summary(interestSum, overpaymentProvisionSum, totalLostSum, BigDecimal.valueOf(42L)));
    }

    /**
     * Method under test: {@link PrintingServiceImpl#printSummary(Summary)}
     */
    @Test
    void testPrintSummary6() {
        PrintingServiceImpl printingServiceImpl = new PrintingServiceImpl();
        Summary summary = mock(Summary.class);
        when(summary.getTotalCapital()).thenReturn(BigDecimal.valueOf(42L));
        when(summary.getTotalLostSum()).thenReturn(BigDecimal.valueOf(42L));
        when(summary.getInterestSum()).thenReturn(BigDecimal.valueOf(42L));
        when(summary.getOverpaymentProvisionSum()).thenReturn(BigDecimal.valueOf(42L));
        printingServiceImpl.printSummary(summary);
        verify(summary).getInterestSum();
        verify(summary).getOverpaymentProvisionSum();
        verify(summary).getTotalCapital();
        verify(summary).getTotalLostSum();
    }
}

