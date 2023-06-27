package com.niit.OrderService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    private String orderId;
    private String dateOfOrder;
    private String timeOfOrder;
    private int noOfItems;
    private String amount;
    private List<Menu> menu;
    private Address address;
}
