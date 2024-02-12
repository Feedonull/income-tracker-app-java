package com.incometracker.incomesourceservice.service.impl;

import com.incometracker.incomesourceservice.model.Source;
import com.incometracker.incomesourceservice.repository.SourceRepository;
import com.incometracker.incomesourceservice.service.SourceService;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;
    @Override
    public Source add(Source source) {
        return sourceRepository.save(source);
    }

    @Override
    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    @Override
    public Optional<Source> findById(Long id) {
        return sourceRepository.findById(id);
    }

    @Override
    public Optional<Source> findByName(String name) {
        return sourceRepository.findByName(name);
    }

    @Override
    public Source update(Source source) {
        if(sourceRepository.findById(source.getId()).isPresent()){
            sourceRepository.saveAndFlush(source);
        }else{
            throw new NotFoundException("Source not registered to be updated, register source first");
        }
        return source;
    }

    @Override
    public String delete(Long id) {
        Optional<Source> source = sourceRepository.findById(id);
        if(source.isPresent()){
            sourceRepository.delete(source.get());
            return "Deleted successfully";
        }else{
            return "No source with id "+id+" found";
        }
    }
}
