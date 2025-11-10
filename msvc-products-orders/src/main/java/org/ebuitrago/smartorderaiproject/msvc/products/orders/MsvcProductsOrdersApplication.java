package org.ebuitrago.smartorderaiproject.msvc.products.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcProductsOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductsOrdersApplication.class, args);
	}

}

