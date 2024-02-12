package com.incometracker.methodservice.service.impl;

import com.incometracker.methodservice.model.Method;
import com.incometracker.methodservice.repository.MethodRepository;
import com.incometracker.methodservice.service.MethodService;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MethodServiceImpl implements MethodService {

    private final MethodRepository methodRepository;
    @Override
    public Method add(Method method) {
        return methodRepository.save(method);
    }

    @Override
    public List<Method> findAll() {
        return methodRepository.findAll();
    }

    @Override
    public Optional<Method> findById(Long id) {
        return methodRepository.findById(id);
    }

    @Override
    public Optional<Method> findByName(String name) {
        return methodRepository.findByName(name);
    }

    @Override
    public Method update(Method method) {
        if(methodRepository.findById(method.getId()).isPresent()){
            methodRepository.saveAndFlush(method);
        }else{
            throw new NotFoundException("Method not registered to be updated, register method first");
        }
        return method;
    }

    @Override
    public String delete(Long id) {
        Optional<Method> method = methodRepository.findById(id);
        if(method.isPresent()){
            methodRepository.delete(method.get());
            return "Deleted successfully";
        }else{
            return "No method with id "+id+" found";
        }
    }
}
