package com.niit.OrderService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    private String street;
    private String city;
    private String houseNo;
    private String pinCode;
}
