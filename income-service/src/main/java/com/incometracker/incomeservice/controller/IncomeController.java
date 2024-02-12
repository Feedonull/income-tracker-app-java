package com.incometracker.incomeservice.controller;

import com.incometracker.incomeservice.dto.ApiResponse;
import com.incometracker.incomeservice.model.Income;
import com.incometracker.incomeservice.service.IncomeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/income")
@AllArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    private final static Logger logger = LoggerFactory.getLogger(Income.class);

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody Income income){

        logger.info("Adding new income");


            Income result = incomeService.add(income);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/income/add")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true,"Income added successfully",result));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll(){

        logger.info("Retrieving all Incomes");

        List<Income> incomes = incomeService.findAll();
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "List retrieved successfully", incomes));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id){

        logger.info("Finding income with id {}", id);

        Optional<Income> income = incomeService.findById(id);
        if(income.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Income retrieved successfully", income));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No income with id: "+id+" found", null));
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody Income income){

        logger.info("Updating income with id {}", income.getId());
        Income result = incomeService.update(income);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Income with id: "+income.getId()+" updated", result));
    }

}
