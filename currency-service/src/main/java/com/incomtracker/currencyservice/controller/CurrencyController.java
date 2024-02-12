package com.incomtracker.currencyservice.controller;

import com.incomtracker.currencyservice.dto.ApiResponse;
import com.incomtracker.currencyservice.model.Currency;
import com.incomtracker.currencyservice.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/currency")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    private final static Logger logger = LoggerFactory.getLogger(Currency.class);
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody Currency currency){

        logger.info("Adding new currency");

        if(currencyService.findByName(currency.getName()).isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true,"Currency already exist",null));
        }else{
            Currency result = currencyService.add(currency);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/currency/add")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true,"Currency added successfully",result));
        }

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll(){

        logger.info("Retrieving all currencies");

        List<Currency> currencies = currencyService.findAll();
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "List retrieved successfully", currencies));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id){

        logger.info("Finding currency with id {}", id);

        Optional<Currency> currency = currencyService.findById(id);
        if(currency.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "currency retrieved successfully", currency));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No currency with id: "+id+" found", null));
        }

    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<ApiResponse> findByName(@PathVariable("name") String name){

        logger.info("Finding currency with name {}", name);

        Optional<Currency> currency = currencyService.findByName(name);
        if(currency.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "currency retrieved successfully", currency));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No currency with name: "+name+" found", null));
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody Currency currency){

        logger.info("Updating currency with name {}", currency.getName());
            Currency result = currencyService.update(currency);
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Currency with name: "+currency.getName()+" updated", result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id){

        logger.info("Deleting currency with id {}", id);
        String result = currencyService.delete(id);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Currency with id: "+id+" "+result, null));
    }




}
