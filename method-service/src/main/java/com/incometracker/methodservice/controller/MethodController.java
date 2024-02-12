package com.incometracker.methodservice.controller;

import com.incometracker.methodservice.dto.ApiResponse;
import com.incometracker.methodservice.model.Method;
import com.incometracker.methodservice.service.MethodService;
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
@RequestMapping("api/v1/method")
@AllArgsConstructor
public class MethodController {

    private final MethodService methodService;
    private final static Logger logger = LoggerFactory.getLogger(Method.class);

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody Method method){

        logger.info("Adding new method");

        if(methodService.findByName(method.getName()).isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true,"Method already exist",null));
        }else{
            Method result = methodService.add(method);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/method/add")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true,"Method added successfully",result));
        }

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll(){

        logger.info("Retrieving all methods");

        List<Method> methods = methodService.findAll();
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "List retrieved successfully", methods));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id){

        logger.info("Finding currency with id {}", id);

        Optional<Method> method = methodService.findById(id);
        if(method.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Method retrieved successfully", method));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No method with id: "+id+" found", null));
        }

    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<ApiResponse> findByName(@PathVariable("name") String name){

        logger.info("Finding method with name {}", name);

        Optional<Method> method = methodService.findByName(name);
        if(method.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "method retrieved successfully", method));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No method with name: "+name+" found", null));
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody Method method){

        logger.info("Updating method with name {}", method.getName());
        Method result = methodService.update(method);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Method with name: "+method.getName()+" updated", result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id){

        logger.info("Deleting method with id {}", id);
        String result = methodService.delete(id);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Method with id: "+id+" "+result, null));
    }

}
