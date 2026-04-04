package com.teju.finance.controller;

import com.teju.finance.entity.FinancialRecord;
import com.teju.finance.exception.UnauthorizedException;
import com.teju.finance.service.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}