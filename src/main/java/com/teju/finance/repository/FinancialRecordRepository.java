package com.teju.finance.repository;

import com.teju.finance.entity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(String type);

    List<FinancialRecord> findByCategory(String category);
}