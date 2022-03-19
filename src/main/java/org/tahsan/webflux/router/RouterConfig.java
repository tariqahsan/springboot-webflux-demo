package org.tahsan.webflux.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.tahsan.webflux.handler.CustomerHandler;
import org.tahsan.webflux.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
    	System.out.println("In RouterFunction ...");
        return RouterFunctions.route()
                .GET("/router/customers",handler::loadCustomers)
                .GET("/router/customers/stream",streamHandler::getCustomers)
                .GET("/router/customer/{input}",handler::findCustomer)
                .POST("/router/customer/save",handler::saveCustomer)
                .build();

    }
}
