package com.niit.OrderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Menu Item already exists in cart same item cannot be added twice to cart")
public class MenuAlreadyExists extends Exception{
}
