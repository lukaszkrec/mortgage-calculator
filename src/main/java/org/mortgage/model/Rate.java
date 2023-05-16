package org.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;

@Data
@With
@Builder
@AllArgsConstructor
public class Rate {


    private final BigDecimal rateNumber;

    private final TimePoint timePoint;

    private final RateAmounts rateAmounts;

    private final MortgageResidual mortgageResidual;

    private final MortgageReference mortgageReference;
}
