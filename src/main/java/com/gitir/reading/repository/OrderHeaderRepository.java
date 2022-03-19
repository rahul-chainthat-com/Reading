package com.gitir.reading.repository;

import com.gitir.reading.entity.Customer;
import com.gitir.reading.entity.OrderHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    @Query(
            value = "SELECT to_char(a.created_at, 'MM YYYY') month, sum(b.amount*b.quantity) totalPurchasedAmount, count(b.id) totalBookCount, count(distinct a.id) totalOrderCount\n" +
                    "FROM ORDER_HEADER a, ORDER_ITEMS b\n" +
                    "where a.id = b.header_id\n" +
                    "and a.customer_id = ?1\n" +
                    "group by to_char(a.created_at, 'MM YYYY')",
            nativeQuery = true)
    List<StatisticForOneMonthConverter> generateMonthlyReport(Long customerId);

    Page<OrderHeader> findAllByCustomer(Customer customer, Pageable pageable);
}
