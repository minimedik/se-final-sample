package com.example.finalsample;

import com.example.finalsample.entities.Investment;
import com.example.finalsample.repositories.InvestmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class FinalSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalSampleApplication.class, args);
    }

    //
    // init the data is required when using h2
    @Bean
    CommandLineRunner commandLineRunner(InvestmentRepository investmentRepository) {

        return args -> {
            investmentRepository.save(new Investment(null, "115", "Jasper Diaz", 15000.0, 5,"Savings-Deluxe"));
            investmentRepository.save(new Investment(null, "112", "Zanip Mendez", 5000.0, 2,"Savings-Deluxe"));
            investmentRepository.save(new Investment(null, "113", "Geronima Esper", 6000.0, 5,"Savings-Regular"));
        };
    }

}


