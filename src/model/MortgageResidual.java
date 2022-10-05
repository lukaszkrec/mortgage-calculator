package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortgageResidual {

    private final BigDecimal residualAmount;
    private final BigDecimal residualDuration;

    public MortgageResidual(final BigDecimal residualAmount, final BigDecimal residualDuration) {
        this.residualAmount = residualAmount;
        this.residualDuration = residualDuration;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getResiudualDuration() {
        return residualDuration;
    }


}
