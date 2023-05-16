package org.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class MortgageReference {

    private final BigDecimal referenceAmount;

    private final BigDecimal referenceDuration;
}
