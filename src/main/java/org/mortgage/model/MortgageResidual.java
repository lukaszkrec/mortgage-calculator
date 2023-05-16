package org.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class MortgageResidual {

    private final BigDecimal residualAmount;

    private final BigDecimal residualDuration;
}
