package org.tahsan.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.tahsan.webflux.dao.CustomerDao;
import org.tahsan.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;


    public Mono<ServerResponse> loadCustomers(ServerRequest request){
    	//A Reactive Streams Publisher with basic rx operators that emits at most one item 
    	//via the onNext signal then terminates with an onComplete signal 
    	//(successful Mono,with or without value), or only emits a single onError signal (failed Mono). 
    	System.out.println("In loadCustomers returning Flux ...");
        Flux<Customer> customerList = dao.getCustomerList();
        return ServerResponse.ok().body(customerList,Customer.class);
    }


    public Mono<ServerResponse> findCustomer(ServerRequest request){
    	System.out.println("In loadCustomers returning Mono ...");
    	int customerId= Integer.valueOf( request.pathVariable("input"));
    	// dao.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
        Mono<Customer> customerMono = dao.getCustomerList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono,Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }



}
