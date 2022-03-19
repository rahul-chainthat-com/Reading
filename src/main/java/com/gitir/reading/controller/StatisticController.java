package com.gitir.reading.controller;

import com.gitir.reading.model.response.MontlyReportResponse;
import com.gitir.reading.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{customerId}/monthly-report")
    public ResponseEntity<MontlyReportResponse> getMonthlyStatistics(@PathVariable Long customerId) {
        return ResponseEntity.ok(statisticService.getMonthlyReport(customerId));
    }
}
