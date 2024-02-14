package com.incometracker.incomeservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incometracker.incomeservice.client.CurrencyClient;
import com.incometracker.incomeservice.client.MethodClient;
import com.incometracker.incomeservice.client.SourceClient;
import com.incometracker.incomeservice.dto.ApiResponse;
import com.incometracker.incomeservice.dto.IncomeDto;
import com.incometracker.incomeservice.model.Currency;
import com.incometracker.incomeservice.model.Income;
import com.incometracker.incomeservice.model.Method;
import com.incometracker.incomeservice.model.Source;
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
    private CurrencyClient currencyClient;
    private SourceClient sourceClient;
    private MethodClient methodClient;
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
        Optional<Income> income = incomeRepository.findById(id);
        return income;
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
    public Currency getIncomeCurrency(Long id) {
        ApiResponse apiResponse = currencyClient.findById(id).getBody();
        ObjectMapper mapper = new ObjectMapper();
        Currency currency = mapper.convertValue(apiResponse.getData(), Currency.class);
        return currency;
    }

    @Override
    public Source getIncomeSource(Long id) {
        ApiResponse apiResponse = sourceClient.findById(id).getBody();
        ObjectMapper mapper = new ObjectMapper();
        Source source = mapper.convertValue(apiResponse.getData(), Source.class);
        return source;
    }

    @Override
    public Method getIncomeMethod(Long id) {
        ApiResponse apiResponse = methodClient.findById(id).getBody();
        ObjectMapper mapper = new ObjectMapper();
        Method method = mapper.convertValue(apiResponse.getData(), Method.class);
        return method;
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
