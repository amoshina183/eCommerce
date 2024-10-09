package com.example.comm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.comm.entity.Product;
import com.example.comm.entity.Test;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
		
		Test t = context.getBean(Test.class);
		t.display();
	
	}

}
