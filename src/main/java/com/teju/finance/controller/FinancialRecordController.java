package com.teju.finance.controller;

import com.teju.finance.entity.FinancialRecord;
import com.teju.finance.exception.UnauthorizedException;
import com.teju.finance.service.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService service;

    @PostMapping
    public FinancialRecord createRecord(
            @RequestHeader("role") String role,
            @Valid @RequestBody FinancialRecord record) {

        if (!role.equals("ADMIN")) {
        	throw new UnauthorizedException("Only ADMIN can create records");
        }

        return service.createRecord(record);
    }

    @GetMapping
    public List<FinancialRecord> getRecords(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getRecords(type, category, page, size);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(
            @RequestHeader("role") String role,
            @PathVariable Long id) {

        if (!role.equals("ADMIN")) {
        	throw new UnauthorizedException("Only ADMIN can delete records");
        }

        service.deleteRecord(id);
    }
    
    @PutMapping("/{id}")
    public FinancialRecord updateRecord(
            @RequestHeader("role") String role,
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecord record) {

        if (!role.equals("ADMIN")) {
            throw new UnauthorizedException("Only ADMIN can update records");
        }

        return service.updateRecord(id, record);
    }
    
   
    @GetMapping("/summary/income")
    public double totalIncome(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getTotalIncome();
    }

    @GetMapping("/summary/expense")
    public double totalExpense(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getTotalExpense();
    }

    @GetMapping("/summary/balance")
    public double netBalance(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getNetBalance();
    }

    @GetMapping("/summary/recent")
    public List<FinancialRecord> recentRecords(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getRecentRecords();
    }

    @GetMapping("/summary/category")
    public Map<String, Double> categoryTotals(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getCategoryTotals();
    }

    @GetMapping("/summary/monthly")
    public Map<String, Double> monthlyTrends(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getMonthlyTrends();
    }

    @GetMapping("/summary/weekly")
    public Map<Integer, Double> weeklyTrends(@RequestHeader("role") String role) {

        if ("VIEWER".equals(role)) {
            throw new UnauthorizedException("Viewer cannot access summary");
        }

        return service.getWeeklyTrends();
    }
}