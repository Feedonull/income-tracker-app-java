package com.incometracker.methodservice.service;

import com.incometracker.methodservice.model.Method;

import java.util.List;
import java.util.Optional;

public interface MethodService {

    Method add(Method method);
    List<Method> findAll();
    Optional<Method> findById(Long id);
    Optional<Method> findByName(String name);
    Method update(Method method);
    String delete(Long id);

}
