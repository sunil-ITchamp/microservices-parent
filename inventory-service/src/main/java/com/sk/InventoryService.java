package com.sk;

import com.sk.inventory.Inventory;
import com.sk.inventory.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InventoryService {
    public static void main(String[] args) {
        SpringApplication.run(InventoryService.class, args);
    }

    @GetMapping
    public String hello(){
        return "hello from Inventory service !!";
    }
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("iphone");
            inventory1.setQuantity(1);

            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("samsung");
            inventory2.setQuantity(1);

            Inventory inventory3 = new Inventory();
            inventory3.setSkuCode("oneplus");
            inventory3.setQuantity(0);

            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
            inventoryRepository.save(inventory3);
        };
    }
}
