package org.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Overpayment {

    public static final String REDUCE_RATE = "REDUCE_RATE";

    public static final String REDUCE_PERIOD = "REDUCE_PERIOD";

    private final BigDecimal amount;

    private final BigDecimal provisionAmount;
}
