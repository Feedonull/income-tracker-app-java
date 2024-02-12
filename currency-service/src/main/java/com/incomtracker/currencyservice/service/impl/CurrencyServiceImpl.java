package com.incomtracker.currencyservice.service.impl;

import com.incomtracker.currencyservice.model.Currency;
import com.incomtracker.currencyservice.repository.CurrencyRepository;
import com.incomtracker.currencyservice.service.CurrencyService;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    @Override
    public Currency add(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> findById(Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Optional<Currency> findByName(String name) {
        return currencyRepository.findByName(name);
    }

    @Override
    public Currency update(Currency currency) {

        if(currencyRepository.findById(currency.getId()).isPresent()){
            currencyRepository.saveAndFlush(currency);
        }else{
            throw new NotFoundException("currency not registered to be updated, register currency first");
        }
        return currency;
    }

    @Override
    public String delete(Long id) {

        Optional<Currency> currency = currencyRepository.findById(id);
        if(currency.isPresent()){
            currencyRepository.delete(currency.get());
            return "Deleted successfully";
        }else{
            return "No currency with id "+id+" found";
        }

    }
}
