package com.example.demo;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PartRepository partRepository, ProductRepository productRepository) {
		return args -> {

			List<Part> parts = (List<Part>) partRepository.findAll();
			List<Product> products = (List<Product>) productRepository.findAll();

			if(parts.isEmpty() && products.isEmpty()) {
				InhousePart inhousePart = new InhousePart();
				inhousePart.setName("Computer case");
				inhousePart.setPrice(12.99);
				inhousePart.setInv(5);
				partRepository.save(inhousePart);



				//New Products
				//productRepository.save();
			}
			//*/

			for (Part part : parts) {
				System.out.println(part);
			}
			for (Product product : products) {
				System.out.println(product);
			}
		};
	}
}
