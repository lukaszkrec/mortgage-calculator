package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {
    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);

    private LocalDate repaymentStartDate = LocalDate.of(2022, 1, 6);


    private BigDecimal wiborPercent = new BigDecimal("1.73");

    private BigDecimal amount = new BigDecimal("30000");

    private BigDecimal monthsDuration = BigDecimal.valueOf(180);

    private MortgageType mortgageType = MortgageType.CONSTANT;

    private BigDecimal marginPercent = new BigDecimal("1.9");
    private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);
    private BigDecimal overpaymentProvisionMonth = BigDecimal.valueOf(36);


    private BigDecimal overpaymentStartMonth = BigDecimal.valueOf(1);
    private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            5, BigDecimal.valueOf(10000),
            6, BigDecimal.valueOf(10000),
            7, BigDecimal.valueOf(10000),
            8, BigDecimal.valueOf(10000)
    );

    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;

    private boolean mortgagePrintPayoffsSchedule = true;

    private Integer mortgageRateNumberToPrint = 1;

    public InputData withRepaymentStartDay(LocalDate repaymentStartDay) {
        this.repaymentStartDate = repaymentStartDay;
        return this;
    }

    public InputData withWiborPercent(BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withMonthsDuration(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withType(MortgageType mortgageType) {
        this.mortgageType = mortgageType;
        return this;
    }

    public InputData withBankMarginPercent(BigDecimal bankMarginPercent) {
        this.marginPercent = bankMarginPercent;
        return this;
    }

    public InputData withOverpaymentProvisionPercent(BigDecimal overpaymentProvisionPercent) {
        this.overpaymentProvisionPercent = overpaymentProvisionPercent;
        return this;
    }

    public InputData withOverpaymentProvisionMonth(BigDecimal overpaymentProvisionMonth) {
        this.overpaymentProvisionMonth = overpaymentProvisionMonth;
        return this;
    }

    public InputData withOverpaymentStartMonth(BigDecimal overpaymentStartMonth) {
        this.overpaymentStartMonth = overpaymentStartMonth;
        return this;
    }

    public InputData withOverpaymentSchema(Map<Integer, BigDecimal> overpaymentSchema) {
        this.overpaymentSchema = overpaymentSchema;
        return this;
    }

    public InputData withOverpaymentReduceWay(String overpaymentReduceWay) {
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    }

    public InputData withMortgagePrintPayoffsSchedule(boolean mortgagePrintPayoffsSchedule) {
        this.mortgagePrintPayoffsSchedule = mortgagePrintPayoffsSchedule;
        return this;
    }

    public InputData withMortgageRateNumberToPrint(Integer mortgageRateNumberToPrint) {
        this.mortgageRateNumberToPrint = mortgageRateNumberToPrint;
        return this;
    }

    public LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    public MortgageType getRateType() {
        return mortgageType;
    }

    public BigDecimal getOverpaymentStartMonth() {
        return overpaymentStartMonth;
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {
        return overpaymentSchema;
    }

    public BigDecimal getMarginPercent() {
        return marginPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionMonth() {
        return overpaymentProvisionMonth;
    }

    public boolean isMortgagePrintPayoffsSchedule() {
        return mortgagePrintPayoffsSchedule;
    }

    public Integer getMortgageRateNumberToPrint() {
        return mortgageRateNumberToPrint;
    }

    public String getOverpaymentReduceWay() {
        return overpaymentReduceWay;
    }

    public BigDecimal getInterestPercent() {
        return wiborPercent.add(marginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(marginPercent).setScale(2, RoundingMode.HALF_UP);
    }
}
