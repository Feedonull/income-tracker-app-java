package com.incometracker.incomeservice.service;

import com.incometracker.incomeservice.model.Currency;
import com.incometracker.incomeservice.model.Income;
import com.incometracker.incomeservice.model.Method;
import com.incometracker.incomeservice.model.Source;

import java.util.List;
import java.util.Optional;

public interface IncomeService {

    Income add(Income income);
    List<Income> findAll();
    Optional<Income> findById(Long id);
    Income update(Income income);
    Currency getIncomeCurrency(Long id);
    Source getIncomeSource(Long id);
    Method getIncomeMethod(Long id);
    String delete(Long id);
}
