package com.example.inventoryservice.util;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader  implements CommandLineRunner {
    private final InventoryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Inventory inventory1 = new Inventory();
        Inventory inventory2 = new Inventory();

        inventory1.setSkuCode("iphone_15");
        inventory1.setQuantity(100);

        inventory2.setSkuCode("ihone_14_pro");
        inventory2.setQuantity(0);
        repository.save(inventory1);
        repository.save(inventory2);
    }
}
