package com.teju.finance.service;

import com.teju.finance.entity.FinancialRecord;
import com.teju.finance.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}