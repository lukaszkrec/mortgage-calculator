package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mortgage.model.MortgageReference;
import org.mortgage.model.MortgageResidual;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;
import org.mortgage.model.RateAmounts;
import org.mortgage.model.Summary;
import org.mortgage.model.TimePoint;

class SummaryServiceFactoryTest {
    /**
     * Method under test: {@link SummaryServiceFactory#create()}
     */
    @Test
    void testCreate() {
        SummaryService actualCreateResult = SummaryServiceFactory.create();
        Summary actualCalculateSummaryResult = actualCreateResult.calculateSummary(new ArrayList<>());
        BigDecimal interestSum = actualCalculateSummaryResult.getInterestSum();
        assertSame(interestSum, actualCalculateSummaryResult.getTotalLostSum());
        assertSame(interestSum, actualCalculateSummaryResult.getOverpaymentProvisionSum());
        assertSame(interestSum, actualCalculateSummaryResult.getTotalCapital());
        assertEquals("0", interestSum.toString());
    }

    /**
     * Method under test: {@link SummaryServiceFactory#create()}
     */
    @Test
    void testCreate2() {
        SummaryService actualCreateResult = SummaryServiceFactory.create();
        ArrayList<Rate> rateList = new ArrayList<>();
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
        rateList.add(new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual,
                new MortgageReference(referenceAmount, BigDecimal.valueOf(42L))));
        Summary actualCalculateSummaryResult = actualCreateResult.calculateSummary(rateList);
        BigDecimal interestSum = actualCalculateSummaryResult.getInterestSum();
        assertEquals(rateNumber, interestSum);
        BigDecimal overpaymentProvisionSum = actualCalculateSummaryResult.getOverpaymentProvisionSum();
        assertEquals(rateNumber, overpaymentProvisionSum);
        BigDecimal totalLostSum = actualCalculateSummaryResult.getTotalLostSum();
        BigDecimal totalCapital = actualCalculateSummaryResult.getTotalCapital();
        assertEquals(totalLostSum, totalCapital);
        assertEquals("42", overpaymentProvisionSum.toString());
        assertEquals("84", totalCapital.toString());
        assertEquals("84", totalLostSum.toString());
        assertEquals("42", interestSum.toString());
    }
}

