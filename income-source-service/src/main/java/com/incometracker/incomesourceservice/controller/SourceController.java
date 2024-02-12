package com.incometracker.incomesourceservice.controller;

import com.incometracker.incomesourceservice.dto.ApiResponse;
import com.incometracker.incomesourceservice.model.Source;
import com.incometracker.incomesourceservice.service.SourceService;
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
@RequestMapping("api/v1/source")
@AllArgsConstructor
public class SourceController {

    private final SourceService sourceService;
    private final static Logger logger = LoggerFactory.getLogger(Source.class);

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody Source source){

        logger.info("Adding new source");

        if(sourceService.findByName(source.getName()).isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true,"Source already exist",null));
        }else{
            Source result = sourceService.add(source);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/source/add")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location)
                    .body(new ApiResponse(true,"Source added successfully",result));
        }

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll(){

        logger.info("Retrieving all Sources");

        List<Source> sources = sourceService.findAll();
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "List retrieved successfully", sources));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id){

        logger.info("Finding source with id {}", id);

        Optional<Source> source = sourceService.findById(id);
        if(source.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Source retrieved successfully", source));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No source with id: "+id+" found", null));
        }

    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<ApiResponse> findByName(@PathVariable("name") String name){

        logger.info("Finding source with name {}", name);

        Optional<Source> source = sourceService.findByName(name);
        if(source.isPresent()){
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "source retrieved successfully", source));
        }else{
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "No source with name: "+name+" found", null));
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@RequestBody Source source){

        logger.info("Updating source with name {}", source.getName());
        Source result = sourceService.update(source);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Source with name: "+source.getName()+" updated", result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id){

        logger.info("Deleting source with id {}", id);
        String result = sourceService.delete(id);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Source with id: "+id+" "+result, null));
    }

}
