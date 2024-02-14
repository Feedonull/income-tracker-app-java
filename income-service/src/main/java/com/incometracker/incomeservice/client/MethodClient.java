package com.incometracker.incomeservice.client;

import com.incometracker.incomeservice.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("METHOD-SERVICE")
public interface MethodClient {

    @GetMapping("api/v1/method/find/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id);

}
