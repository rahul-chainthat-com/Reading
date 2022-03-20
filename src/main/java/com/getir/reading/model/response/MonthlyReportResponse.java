package com.getir.reading.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyReportResponse {
    private List<StatisticForOneMonth> statisticForOneMonthList;
}
