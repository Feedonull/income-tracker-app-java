package com.incometracker.incomesourceservice.repository;

import com.incometracker.incomesourceservice.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    Optional<Source> findByName(String name);

}
