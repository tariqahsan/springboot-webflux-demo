package org.tahsan.webflux.dao;

import org.springframework.stereotype.Component;
import org.tahsan.webflux.dto.Customer;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {


    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers()  {
    	System.out.println("In getCustomers ...");
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }


    public Flux<Customer> getCustomersStream()  {
    	System.out.println("In getCustomersStream ...");
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }


    public Flux<Customer> getCustomerList()  {
    	System.out.println("In getCustomerList ...");
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }
}
