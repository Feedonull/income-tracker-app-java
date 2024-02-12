package com.incometracker.incomesourceservice.service;

import com.incometracker.incomesourceservice.model.Source;

import java.util.List;
import java.util.Optional;

public interface SourceService {

    Source add(Source source);
    List<Source> findAll();
    Optional<Source> findById(Long id);
    Optional<Source> findByName(String name);
    Source update(Source source);
    String delete(Long id);

}
