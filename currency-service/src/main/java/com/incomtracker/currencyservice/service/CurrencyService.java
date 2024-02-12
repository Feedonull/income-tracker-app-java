package com.incomtracker.currencyservice.service;

import com.incomtracker.currencyservice.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    Currency add(Currency currency);
    List<Currency> findAll();
    Optional<Currency> findById(Long id);
    Optional<Currency> findByName(String name);
    Currency update(Currency currency);
    String delete(Long id);

}
