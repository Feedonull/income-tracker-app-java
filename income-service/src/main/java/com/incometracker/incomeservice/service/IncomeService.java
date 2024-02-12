package com.incometracker.incomeservice.service;

import com.incometracker.incomeservice.model.Income;

import java.util.List;
import java.util.Optional;

public interface IncomeService {

    Income add(Income income);
    List<Income> findAll();
    Optional<Income> findById(Long id);
    Income update(Income income);
    String delete(Long id);
}
