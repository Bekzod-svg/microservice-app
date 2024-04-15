package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderListItems;
import com.example.orderservice.repository.OrderRepository;
import event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderListItems> orderListItems = orderRequest.getOrderListItems()
                .stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto)
                ).toList();

        order.setOrderListItems(orderListItems);
        List<String> skuCodes = order.getOrderListItems().stream().map(item -> item.getSkuCode()).toList();

        InventoryResponse[] res = webClientBuilder.build() .get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        Boolean productsInStock = Arrays.stream(res).allMatch(InventoryResponse::getIsInStock);

        if(productsInStock){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order placed successfully!";
        }else {
            throw new IllegalArgumentException("Product is not in stock");
        }


    }

    public OrderListItems mapToDto(OrderLineItemsDto itemsDto){
        return OrderListItems.builder()
                .skuCode(itemsDto.getSkuCode())
                .price(itemsDto.getPrice())
                .quantity(itemsDto.getQuantity())
                .build();
    }

}
