package com.teju.finance.repository;

import com.teju.finance.entity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(String type);

    List<FinancialRecord> findByCategory(String category);

    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'INCOME'")
    Double getTotalIncome();

    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'EXPENSE'")
    Double getTotalExpense();
}