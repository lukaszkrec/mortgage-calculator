package org.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;

@With
@Data
@Builder
@AllArgsConstructor
public class TimePoint {

    private final BigDecimal year;

    private final BigDecimal month;

    private final LocalDate date;
}
