package com.sk.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/checkstock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> checkStockForEachSku(@RequestParam List<String> skuCodeList){
        log.info("Received inventory check request for skuCode: {}", skuCodeList);
        List<InventoryResponse> inventoryResponseList =
                inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
                .map(
                        inventory -> InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity()>=1)
                                .build()
                ).toList();
        return inventoryResponseList;
    }




}
