package com.incometracker.incomeservice.dto;

import com.incometracker.incomeservice.model.Currency;
import com.incometracker.incomeservice.model.Method;
import com.incometracker.incomeservice.model.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomeDto {

    private Long id;
    private Long userId;
    private Double amount;
    private Currency currency;
    private Source source;
    private Method method;
    private LocalDateTime createdAt;
    private String location;

}
