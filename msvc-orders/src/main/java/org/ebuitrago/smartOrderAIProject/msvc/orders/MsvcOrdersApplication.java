package org.ebuitrago.smartOrderAIProject.msvc.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@SpringBootApplication
public class MsvcOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcOrdersApplication.class, args);
	}

}
