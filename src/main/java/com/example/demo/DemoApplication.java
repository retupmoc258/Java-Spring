package com.example.demo;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;


import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PartService partService, ProductService productService) {
		return args -> {

			List<Part> parts = partService.findAll();
			List<Product> products = productService.findAll();

			if(!parts.isEmpty() || !products.isEmpty()) { return;}
			//If both are empty, run this code:
			//New Parts
			InhousePart computerCase = new InhousePart("Computer case", 12.99, 50, 1, 2, 100);
			partService.save(computerCase);

			OutsourcedPart motherboard = new OutsourcedPart("Motherboard", 119.99, 30, "ASUS", 2, 100);
			partService.save(motherboard);

			OutsourcedPart memoryStick = new OutsourcedPart("8 GB memory stick", 89.99, 100, "Western Digital", 2, 100);
			partService.save(memoryStick);

			OutsourcedPart memoryStickPack = new OutsourcedPart("8 GB memory stick (2 pack)", 179.98, 30, "Western Digital", 0, 30);
			partService.save(memoryStickPack);

			OutsourcedPart averageCPU = new OutsourcedPart("2.5 GHz CPU", 479.99, 60, "AMD", 1, 40);
			partService.save(averageCPU);

			OutsourcedPart midgradeCPU = new OutsourcedPart("3.0 GHz CPU", 749.99, 60, "AMD", 1, 40);
			partService.save(midgradeCPU);

			OutsourcedPart fastCPU = new OutsourcedPart("3.5 GHz CPU", 1149.99, 30, "AMD", 1, 40);
			partService.save(fastCPU);

			InhousePart improvedFan = new InhousePart("Improved Fan", 39.99, 50, 2, 5, 100);
			partService.save(improvedFan);


			//New Products

			//Lambda function to reduce repetition in code.
			java.util.function.BiFunction<Product, List<Part>, Boolean> addParts = (product,  partList) -> {
				try {
					product.addParts(partList);
					productService.save(product);
					for (Part part : partList) {
						part.getProducts().add(product);
						partService.save(part);
					}
				}
				catch (Exception e) {
					System.out.println("   Error message: ");
					System.out.println(e.getMessage());
					return false;
				}
				return true;
			};

			Product workComputer = new Product("Work Computer", 799.99, 4);
			productService.save(workComputer);
			addParts.apply(workComputer, List.of(computerCase, motherboard, memoryStick, averageCPU));

			Product studioComputer = new Product("Studio Computer", 1199.99, 5);
			productService.save(studioComputer);
			addParts.apply(studioComputer, List.of(computerCase, motherboard, memoryStickPack, midgradeCPU));

			Product basicGameComputer = new Product("Basic Game Computer", 1249.99, 6);
			productService.save(basicGameComputer);
			addParts.apply(basicGameComputer, List.of(computerCase, motherboard, memoryStickPack, midgradeCPU, improvedFan));

			Product ultimateGameComputer = new Product("Ultimate Game Computer", 1699.99, 3);
			productService.save(ultimateGameComputer);
			addParts.apply(ultimateGameComputer, List.of(computerCase, motherboard, memoryStickPack, fastCPU, improvedFan));

			Product workstationComputer = new Product("Workstation Computer", 1099.99, 5);
			productService.save(workstationComputer);
			addParts.apply(workstationComputer, List.of(computerCase, motherboard, memoryStick, midgradeCPU));

		};
	}
}
