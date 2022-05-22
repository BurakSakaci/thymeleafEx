package com.example.thymeleafExample.controller;

import com.example.thymeleafExample.dataAccess.CustomerRepository;
import com.example.thymeleafExample.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomersController {
    Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping({"/list", "/"})
    public ModelAndView getAllCustomers(){
        ModelAndView modelAndView = new ModelAndView("list-customers");
        modelAndView.addObject("customers", customerRepository.findAll());
        logger.info("list");
        return modelAndView;
    }

    @GetMapping("/addCustomerForm")
    public ModelAndView addCustomerForm(){
        ModelAndView modelAndView = new ModelAndView("add-customer-form");
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        logger.info("adding customer");
        return modelAndView;
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer){
        customerRepository.save(customer);
        logger.info("customer added");
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView shouwUpdateForm(@RequestParam Long customerId){
        ModelAndView modelAndView = new ModelAndView("add-customer-form");
        Customer customer = customerRepository.findById(customerId).get();
        modelAndView.addObject("customer", customer);
        logger.info("customer save");
        return modelAndView;
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam Long customerId){
        customerRepository.deleteById(customerId);
        logger.info("customer delete");
        return "redirect:/list";
    }




}
