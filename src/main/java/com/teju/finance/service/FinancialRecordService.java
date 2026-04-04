package com.teju.finance.service;

import com.teju.finance.entity.FinancialRecord;
import com.teju.finance.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    public FinancialRecord createRecord(FinancialRecord record) {
        return repository.save(record);
    }

    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }
    
    public List<FinancialRecord> getRecords(String type, String category, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        // Filter by type
        if (type != null) {
            return repository.findByType(type);
        }

        // Filter by category
        if (category != null) {
            return repository.findByCategory(category);
        }

        // Pagination (default)
        return repository.findAll(pageable).getContent();
    }

    public List<FinancialRecord> getByType(String type) {
        return repository.findByType(type);
    }

    public List<FinancialRecord> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }
    
    public FinancialRecord updateRecord(Long id, FinancialRecord updatedRecord) {

        FinancialRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(updatedRecord.getAmount());
        record.setType(updatedRecord.getType());
        record.setCategory(updatedRecord.getCategory());
        record.setDate(updatedRecord.getDate());
        record.setDescription(updatedRecord.getDescription());

        return repository.save(record);
    }
    
    //dashboard summary API
 // TOTAL INCOME
    public double getTotalIncome() {
        Double income = repository.getTotalIncome();
        return income != null ? income : 0;
    }

    // TOTAL EXPENSE
    public double getTotalExpense() {
        Double expense = repository.getTotalExpense();
        return expense != null ? expense : 0;
    }

    // NET BALANCE
    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    // RECENT RECORDS (last 5 by date)
    public List<FinancialRecord> getRecentRecords() {
        return repository.findAll()
                .stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .toList();
    }

    // CATEGORY-WISE TOTALS
    public Map<String, Double> getCategoryTotals() {

        List<FinancialRecord> records = repository.findAll();

        Map<String, Double> categoryMap = new HashMap<>();

        for (FinancialRecord record : records) {
            categoryMap.put(
                    record.getCategory(),
                    categoryMap.getOrDefault(record.getCategory(), 0.0) + record.getAmount()
            );
        }

        return categoryMap;
    }
    //MonthlyTrends
    public Map<String, Double> getMonthlyTrends() {

        List<FinancialRecord> records = repository.findAll();

        Map<String, Double> monthlyMap = new HashMap<>();

        for (FinancialRecord record : records) {

            String month = record.getDate().getYear() + "-" + record.getDate().getMonthValue();

            monthlyMap.put(
                    month,
                    monthlyMap.getOrDefault(month, 0.0) + record.getAmount()
            );
        }

        return monthlyMap;
    }
    //weekly trends
    public Map<Integer, Double> getWeeklyTrends() {

        List<FinancialRecord> records = repository.findAll();

        Map<Integer, Double> weeklyMap = new HashMap<>();

        for (FinancialRecord record : records) {

            int week = record.getDate().getDayOfYear() / 7;

            weeklyMap.put(
                    week,
                    weeklyMap.getOrDefault(week, 0.0) + record.getAmount()
            );
        }

        return weeklyMap;
    }
    
}