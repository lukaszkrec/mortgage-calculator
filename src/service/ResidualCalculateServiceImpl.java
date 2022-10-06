package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResidualCalculateServiceImpl implements ResidualCalculateService {

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {
        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, inputData.getAmount());
        BigDecimal residualDuration = inputData.getMonthsDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, final InputData inputData, Rate previousRate) {
        MortgageResidual residual = previousRate.getMortgageResidual();

        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, residual.getResidualAmount());
        BigDecimal residualDuration = residual.getResiudualDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    private BigDecimal calculateResidualDuration(
            InputData inputData,
            BigDecimal residualAmount,
            BigDecimal previousResidualDuration,
            RateAmounts rateAmounts
    ) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            switch (inputData.getRateType()) {
                case CONSTANT:
                    return calculateConstantResidualDuration(inputData, residualAmount, rateAmounts);
                case DECREASING:
                    return calculateDecreasingResidualDuration(residualAmount, rateAmounts);
                default:
                    throw new MortgageException();
            }
        } else {
            return previousResidualDuration.subtract(BigDecimal.ONE);
        }

    }

    private BigDecimal calculateDecreasingResidualDuration(BigDecimal residualAmount, RateAmounts rateAmounts) {
        return residualAmount.divide(rateAmounts.getCapitalAmount(), 0, RoundingMode.HALF_UP);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private BigDecimal calculateConstantResidualDuration(
            InputData inputData,
            BigDecimal residualAmount,
            RateAmounts rateAmounts
    ) {
        BigDecimal q = AmountCalculationService.calculateQ(inputData.getInterestPercent());
        BigDecimal xNumerator = rateAmounts.getRateAmount();
        BigDecimal xDenominator = rateAmounts
                .getRateAmount()
                .subtract(residualAmount.multiply(q.subtract(BigDecimal.ONE)));

        BigDecimal x = xNumerator.divide(xDenominator, 10, RoundingMode.HALF_UP);
        BigDecimal y = q;

        BigDecimal logX = BigDecimal.valueOf(Math.log(x.doubleValue()));
        BigDecimal logY = BigDecimal.valueOf(Math.log(y.doubleValue()));

        return logX.divide(logY, 0, RoundingMode.CEILING);
    }

    private BigDecimal calculateResidualAmount(final RateAmounts rateAmounts, final BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
