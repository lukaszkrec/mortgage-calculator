package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PrintingServiceIImpl implements PrintingService {

    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_START_MONTH).append(inputData.getOverpaymentStartMonth()).append(BLANK).append(MONTHS);

        Optional.ofNullable(inputData.getOverpaymentSchema())
                .filter(schema -> schema.size() > 0)
                .ifPresent(schema -> logOverpayment(msg, inputData
                                .getOverpaymentSchema(),
                        inputData.getOverpaymentReduceWay())
                );

        logMessage(msg.toString());

    }

    private void logOverpayment(
            final StringBuilder msg,
            final Map<Integer, BigDecimal> schema,
            final String reduceWay
    ) {
        switch (reduceWay) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortgageException();

        }

        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(NEW_LINE).append(prettyPrintOverpaymentSchema(schema));
    }

    private String prettyPrintOverpaymentSchema(Map<Integer, BigDecimal> schema) {
        StringBuilder schemaMsg = new StringBuilder();
        for (Map.Entry<Integer, BigDecimal> entry : schema.entrySet()) {
            schemaMsg.append(MONTH)
                    .append(entry.getKey())
                    .append(COMMA)
                    .append(AMOUNT)
                    .append(entry.getKey())
                    .append(CURRENCY)
                    .append(NEW_LINE);
        }
        return schemaMsg.toString();
    }

    @Override
    public void printSchedule(final List<Rate> rates, final InputData inputData) {
        if (!inputData.isMortgagePrintPayoffsSchedule()) {
            return;
        }

        int index = 1;

        for (Rate rate : rates) {
            if (rate.getRateNumber().remainder(BigDecimal.valueOf(inputData
                            .getMortgageRateNumberToPrint()))
                    .equals(BigDecimal.ZERO)) {

                String logMessage = String.format(SCHEDULE_TABLE_FORMAT,
                        RATE_NUMBER, rate.getRateNumber(),
                        DATE, rate.getTimePoint().getDate(),
                        YEAR, rate.getTimePoint().getYear(),
                        MONTH, rate.getTimePoint().getMonth(),
                        RATE, rate.getRateAmounts().getRateAmount(),
                        INTEREST, rate.getRateAmounts().getRateAmount(),
                        CAPITAL, rate.getRateAmounts().getRateAmount(),
                        OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
                        LEFT_AMOUNT, rate.getMortgageResidual().getResidualAmount(),
                        LEFT_MONTHS, rate.getMortgageResidual().getResiudualDuration()
                );
                logMessage(logMessage);

                if (index % AmountCalculationService.YEAR.intValue() == 0) {
                    logSeparator(SEPARATOR);
                }
                index++;
            }


        }
        logMessage(NEW_LINE);

    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisionSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSTS_SUM).append(summary.getTotalLostSum()).append(CURRENCY);
        msg.append(NEW_LINE);

        logMessage(msg.toString());

    }

    private void logMessage(String message) {
        System.out.println(message);

    }

    private void logMessage(StringBuilder message) {
        logMessage(message.toString());
    }

    @SuppressWarnings("SameParameterValue")
    private void logSeparator(StringBuilder separator) {
        logMessage(separator);

    }
}
