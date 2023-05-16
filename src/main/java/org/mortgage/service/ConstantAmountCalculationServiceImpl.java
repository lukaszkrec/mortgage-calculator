package org.mortgage.service;

import org.mortgage.model.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantAmountCalculationServiceImpl implements ConstantAmountCalculationService {


    @Override
    public RateAmounts calculate(final InputData inputData, final Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal q = AmountCalculationService.calculateQ(interestPercent);

        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal interestAmount = AmountCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateConstantRateAmount(q, interestAmount, residualAmount, inputData.getAmount(),
                inputData.getMonthsDuration()
        );
        BigDecimal capitalAmount = AmountCalculationService.compareCapitalWithResidual(
                rateAmount.subtract(interestAmount), residualAmount
        );

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public RateAmounts calculate(final InputData inputData, final Overpayment overpayment, final Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal q = AmountCalculationService.calculateQ(interestPercent);

        BigDecimal residualAmount = previousRate.getMortgageResidual().getResidualAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

        BigDecimal interestAmount = AmountCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateConstantRateAmount(q, interestAmount, residualAmount,
                referenceAmount,
                referenceDuration
        );
        BigDecimal capitalAmount = AmountCalculationService.compareCapitalWithResidual(
                rateAmount.subtract(interestAmount),
                residualAmount
        );

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateConstantRateAmount(
            final BigDecimal q,
            final BigDecimal interestAmount,
            final BigDecimal residualAmount,
            final BigDecimal referenceAmount,
            final BigDecimal referenceDuration
    ) {
        BigDecimal rateAmount = referenceAmount
                .multiply(q.pow(referenceDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(referenceDuration.intValue()).subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        return compareRateWithResidual(rateAmount, interestAmount, residualAmount);
    }

    private BigDecimal compareRateWithResidual(
            final BigDecimal rateAmount,
            final BigDecimal interestAmount,
            final BigDecimal residualAmount
    ) {
        if (rateAmount.subtract(interestAmount).compareTo(residualAmount) >= 0) {
            return residualAmount.add(interestAmount);
        }
        return rateAmount;
    }
}
