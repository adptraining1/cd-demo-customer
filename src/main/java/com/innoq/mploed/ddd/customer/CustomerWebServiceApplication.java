package com.innoq.mploed.ddd.customer;

import com.innoq.mploed.ddd.customer.ws.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CustomerWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerWebServiceApplication.class);
    }
}
