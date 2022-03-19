package org.tahsan.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tahsan.webflux.dao.CustomerDao;
import org.tahsan.webflux.dto.Customer;

import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;


    public List<Customer> loadAllCustomers() {
    	System.out.println("In loadAllCustomers ...");
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time in milliseconds: " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
    	System.out.println("In loadAllCustomersStream ...");
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time in milliseconds: " + (end - start));
        return customers;
    }
}
