package com.sk.order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {


    private final OrderRepository orderRepository;

    //private final WebClient webClient;

    private final WebClient.Builder webClientBuilder;

//    @Autowired
//    public OrderController(OrderRepository orderRepository, WebClient webClient){
//        this.orderRepository = orderRepository;
//        this.webClient = webClient;
//    }

    //Create
    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        System.out.println("================== Inside createOrder =================" );
        order.getOrderLineItemsList().stream().forEach(orderLineItem -> System.out.println(orderLineItem));

        //Logic - To check if skuCodes are present in Inventory
        List<String> skuCodesList = order.getOrderLineItemsList().stream()
                .map(orderLineItem -> orderLineItem.getSkuCode()).toList();
        System.out.println("================== calling webclient =================" );
        List<InventoryResponse> inventoryResponseList = webClientBuilder.build().get()
                //.uri("http://localhost:8081/api/inventory/checkstock",uriBuilder -> uriBuilder.queryParam("skuCodeList", skuCodesList).build())
                .uri("http://host.docker.internal:8084/api/inventory/checkstock",uriBuilder -> uriBuilder.queryParam("skuCodeList", skuCodesList).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<InventoryResponse>>() {})
                .block();

        boolean allSkuCodesAvailable = inventoryResponseList.stream().allMatch(inventoryResponse -> inventoryResponse.isInStock());
        System.out.println("================== After webclient call ================= allSkuCodesAvailable = " + allSkuCodesAvailable);
        log.debug(" Here is the reeturn {}", allSkuCodesAvailable);
        if (allSkuCodesAvailable) {
            order.setOrderNumber(UUID.randomUUID().toString());
            Order savedOrder = orderRepository.save(order);
            return ResponseEntity.ok(savedOrder);
        }
        else
        return ResponseEntity.ok(new RuntimeException("Sorry, the stock is not available right now.."));
    }

    //TODO - Alternative create

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
//        log.info("Placing Order");
//        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
//    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<Order> placeOrder(@RequestBody Order orderRequest) {
        log.info("Placing Order");
        return CompletableFuture.supplyAsync(() -> orderRepository.save(orderRequest));
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<?> readOrder(@PathVariable Long id){
        try {
            Order savedOrder = orderRepository.findById(id).
                    orElseThrow(() -> new RuntimeException("Order not found with id = " + id));
            return ResponseEntity.ok(savedOrder);
        } catch(Exception e)
        {
            log.error("Error while finding order in DB " + e.getMessage());
            return ResponseEntity.ok("Error - Order not found with id = " + id);
        }

    }

    //Update
    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Order orderToUpdate = orderRepository.findById(order.getId()).orElseThrow();
        orderToUpdate.setOrderNumber(order.getOrderNumber());
        //@TODO - Update 1 more field
        Order savedOrder = orderRepository.save(orderToUpdate);
        return ResponseEntity.ok(savedOrder);
    }

    @PatchMapping()
    public ResponseEntity<Order> patchOrder(@RequestBody Order order){
        Order orderToUpdate = orderRepository.findById(order.getId()).orElseThrow();
        orderToUpdate.setOrderNumber(order.getOrderNumber());
        //@TODO - Update 1 more field
        Order savedOrder = orderRepository.save(orderToUpdate);
        return ResponseEntity.ok(savedOrder);
    }
    //Delete
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        try{
            orderRepository.deleteById(id);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(" Order deleted");
    }
}

