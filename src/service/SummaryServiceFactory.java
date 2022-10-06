package service;

import model.Rate;
import model.RateAmounts;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(
                    rates,
                    rate -> rate.getRateAmounts().getInterestAmount()
            );
            BigDecimal overpaymentProvisionsSum = calculate(
                    rates,
                    rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount()
            );
            BigDecimal totalLostSum = interestSum.add(overpaymentProvisionsSum);
            BigDecimal totalCapital = calculate(rates, rate -> totalCapital(rate.getRateAmounts()));

            return new Summary(interestSum, overpaymentProvisionsSum, totalLostSum, totalCapital);
        };
    }

    private static BigDecimal totalCapital(final RateAmounts rateAmounts) {
        return rateAmounts.getCapitalAmount().add(rateAmounts.getOverpayment().getAmount());
    }

    private static BigDecimal calculate(List<Rate> rates, Function function) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rate rate : rates) {
            sum = sum.add(function.calculate(rate));

        }
        return sum;
    }


}
