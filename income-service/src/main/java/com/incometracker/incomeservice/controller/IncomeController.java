package com.incometracker.incomeservice.controller;

import com.incometracker.incomeservice.dto.ApiResponse;
import com.incometracker.incomeservice.dto.IncomeDto;
import com.incometracker.incomeservice.model.Currency;
import com.incometracker.incomeservice.model.Income;
import com.incometracker.incomeservice.model.Method;
import com.incometracker.incomeservice.model.Source;
import com.incometracker.incomeservice.service.IncomeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
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
            income.setCreatedAt(LocalDateTime.now());
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
            IncomeDto incomeDto = new IncomeDto();
            incomeDto.setId(income.get().getId());
            incomeDto.setAmount(income.get().getAmount());
            incomeDto.setUserId(income.get().getUserId());
            incomeDto.setLocation(income.get().getLocation());
            incomeDto.setCreatedAt(income.get().getCreatedAt());

            Currency currency = incomeService.getIncomeCurrency(income.get().getCurrencyId());
            incomeDto.setCurrency(currency);

            Source source = incomeService.getIncomeSource(income.get().getSourceId());
            incomeDto.setSource(source);

            Method method = incomeService.getIncomeMethod(income.get().getMethodId());
            incomeDto.setMethod(method);

            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Income retrieved successfully", incomeDto));
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
