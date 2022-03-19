package com.gitir.reading.model.response;

import com.gitir.reading.repository.StatisticForOneMonthConverter;
import com.gitir.reading.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticForOneMonth {

    private String month;
    private String year;
    private Long totalOrderCount;
    private Long totalBookCount;
    private BigDecimal totalPurchasedAmount;

    public static StatisticForOneMonth of(StatisticForOneMonthConverter e) {
        return StatisticForOneMonth.builder()
                .month(DateUtils.getMonth(e.getMonth()))
                .year(DateUtils.getYear(e.getMonth()))
                .totalBookCount(e.getTotalBookCount())
                .totalOrderCount(e.getTotalOrderCount())
                .totalPurchasedAmount(e.getTotalPurchasedAmount())
                .build();
    }
}
