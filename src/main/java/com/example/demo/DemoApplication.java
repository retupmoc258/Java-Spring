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
import java.util.Set;


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

			OutsourcedPart averageCPU = new OutsourcedPart("2.5 GHz CPU", 479.99, 30, "AMD", 1, 40);
			partService.save(averageCPU);

			OutsourcedPart midgradeCPU = new OutsourcedPart("3.0 GHz CPU", 749.99, 30, "AMD", 1, 40);
			partService.save(midgradeCPU);

			OutsourcedPart fastCPU = new OutsourcedPart("3.5 GHz CPU", 1149.99, 30, "AMD", 1, 40);
			partService.save(fastCPU);

			InhousePart improvedFan = new InhousePart("Improved Fan", 39.99, 50, 2, 5, 100);
			partService.save(improvedFan);


			//New Products
			Product workComputer = new Product("Work Computer", 799.99, 4);
			productService.save(workComputer);
			workComputer.addParts(List.of(computerCase, memoryStick, motherboard, averageCPU));
			productService.save(workComputer);

			Product studioComputer = new Product("Studio Computer", 1199.99, 5);
			productService.save(studioComputer);
			studioComputer.addParts(List.of(computerCase, motherboard, memoryStickPack, midgradeCPU));
			productService.save(studioComputer);

			Product basicGameComputer = new Product("Basic Game Computer", 1249.99, 6);
			productService.save(basicGameComputer);
			basicGameComputer.addParts(List.of(computerCase, motherboard, memoryStickPack, midgradeCPU, improvedFan));
			productService.save(basicGameComputer);

			Product ultimateGameComputer = new Product("Ultimate Game Computer", 1699.99, 3);
			productService.save(ultimateGameComputer);
			ultimateGameComputer.addParts(List.of(computerCase, motherboard, memoryStickPack, fastCPU, improvedFan));
			productService.save(ultimateGameComputer);

			Product workstationComputer = new Product("Workstation Computer", 1099.99, 5);
			productService.save(workstationComputer);
			workstationComputer.addParts(List.of(computerCase, motherboard, memoryStick, midgradeCPU));
			productService.save(workstationComputer);


			//Add products to parts - do each ONLY one time.
			computerCase.getProducts().addAll(List.of(workComputer, studioComputer, basicGameComputer, ultimateGameComputer, workstationComputer));
			partService.save(computerCase);

			motherboard.getProducts().addAll(List.of(workComputer, studioComputer, basicGameComputer, ultimateGameComputer, workstationComputer));
			partService.save(motherboard);

			memoryStick.getProducts().addAll(List.of(workComputer, workstationComputer));
			partService.save(memoryStick);

			memoryStickPack.getProducts().addAll(List.of(studioComputer, basicGameComputer, ultimateGameComputer));
			partService.save(memoryStickPack);

			averageCPU.getProducts().add(workComputer);
			partService.save(averageCPU);

			midgradeCPU.getProducts().addAll(List.of(studioComputer, basicGameComputer, workstationComputer));
			partService.save(midgradeCPU);

			fastCPU.getProducts().add(ultimateGameComputer);
			partService.save(fastCPU);

			improvedFan.getProducts().addAll(List.of(ultimateGameComputer, basicGameComputer));
			partService.save(improvedFan);

		};
	}
}
