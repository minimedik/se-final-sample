package com.example.finalsample.repositories;

import com.example.finalsample.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Investment findInvestmentById(long id);
}
