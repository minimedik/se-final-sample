package com.example.finalsample.web;

import com.example.finalsample.entities.Investment;
import com.example.finalsample.entities.ProjectedDTO;
import com.example.finalsample.repositories.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.collection.spi.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class InvestmentController {

    @Autowired
    private InvestmentRepository investmentRepository;

    @GetMapping(path="/")
    public String investments(Model model) {

        List<Investment> investments = investmentRepository.findAll();

        model.addAttribute("listInvestments", investments);
        return "investments";
    }

    @GetMapping(path = "/addInvestment")
    public String addInvestment(Model model) {
        model.addAttribute("investment", new Investment());
        model.addAttribute("action", "addInvestment");
        return "formInvestment";
    }

    @GetMapping(path = "/editInvestment")
    public String addInvestment(Model model, Long id) {
        Investment investment = investmentRepository.findInvestmentById(id);
        model.addAttribute("investment", investment);
        model.addAttribute("action", "editInvestment");
        return "formInvestment";
    }

    @GetMapping(path="/projectInvestment")
    public String projectInvestment(Model model, Long id) {
        Investment investment = investmentRepository.findInvestmentById(id);
        model.addAttribute("investment", investment);
        List<ProjectedDTO> listProjected = new ArrayList<>();
        Double startingAmount = investment.getCustomerDeposit();
        Double interestPercentage = 0.0;

        if (investment.getSavingsType().equals("Savings-Deluxe")) {
            interestPercentage = 15.0;
        } else {
            interestPercentage = 10.0;
        }

        for (int i=1; i <= investment.getNumberOfYears(); i++)
        {
            ProjectedDTO test = new ProjectedDTO();
            test.year =i;
            test.startingAmount = startingAmount;
            test.interest = startingAmount * interestPercentage /100;
            test.endingBalance = test.startingAmount + test.interest;
            listProjected.add(test);
            startingAmount = test.endingBalance;
        }


        model.addAttribute("listProjected", listProjected);

        return "projected";
    }
}
