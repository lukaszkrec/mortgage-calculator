package org.mortgage.service;


import java.math.BigDecimal;
import java.util.List;
import org.mortgage.model.*;

public class SummaryServiceFactory {

    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(rates, rate -> rate.getRateAmounts().getInterestAmount());
            BigDecimal overpaymentProvisionSum = calculate(rates, rate -> rate
                    .getRateAmounts()
                    .getOverpayment()
                    .getProvisionAmount());
            BigDecimal totalLostSum = interestSum.add(overpaymentProvisionSum);
            BigDecimal totalCapital = calculate(rates, rate -> totalCapital(rate.getRateAmounts()));
            return new Summary(interestSum, overpaymentProvisionSum, totalLostSum, totalCapital);
        };
    }

    private static BigDecimal totalCapital(final RateAmounts rateAmounts) {
        return rateAmounts.getCapitalAmount().add(rateAmounts.getOverpayment().getAmount());
    }

    private static BigDecimal calculate(final List<Rate> rates, Function function) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rate rate : rates) {
            sum = sum.add(function.calculate(rate));
        }
        return sum;
    }


}