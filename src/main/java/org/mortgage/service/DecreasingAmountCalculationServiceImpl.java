package org.mortgage.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import org.mortgage.model.*;

public class DecreasingAmountCalculationServiceImpl implements DecreasingAmountCalculationService {

    @Override
    public RateAmounts calculate(final InputData inputData, final Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        final BigDecimal residualAmount = inputData.getAmount();
        final BigDecimal residualDuration = inputData.getMonthsDuration();

        BigDecimal interestAmount = AmountCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = AmountCalculationService.compareCapitalWithResidual(
                calculateDecreasingCapitalAmount(residualAmount, residualDuration), residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public RateAmounts calculate(final InputData inputData, final Overpayment overpayment, final Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = previousRate.getMortgageResidual().getResidualAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

        BigDecimal interestAmount = AmountCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = AmountCalculationService.compareCapitalWithResidual(
                calculateDecreasingCapitalAmount(referenceAmount, referenceDuration), residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateDecreasingCapitalAmount(
            final BigDecimal residualAmount,
            final BigDecimal residualDuration
    ) {
        return residualAmount.divide(residualDuration, 2, RoundingMode.HALF_UP);
    }
}
