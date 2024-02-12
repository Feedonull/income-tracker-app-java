package com.incometracker.incomeservice.service.impl;

import com.incometracker.incomeservice.model.Income;
import com.incometracker.incomeservice.repository.IncomeRepository;
import com.incometracker.incomeservice.service.IncomeService;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    @Override
    public Income add(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    @Override
    public Optional<Income> findById(Long id) {
        return incomeRepository.findById(id);
    }

    @Override
    public Income update(Income income) {
        if(incomeRepository.findById(income.getId()).isPresent()){
            incomeRepository.saveAndFlush(income);
        }else{
            throw new NotFoundException("Entry not registered to be updated, register source first");
        }
        return income;
    }

    @Override
    public String delete(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isPresent()){
            incomeRepository.delete(income.get());
            return "Deleted successfully";
        }else{
            return "No entry with id "+id+" found";
        }
    }
}
