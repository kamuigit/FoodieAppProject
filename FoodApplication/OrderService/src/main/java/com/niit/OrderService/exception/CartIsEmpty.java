package com.niit.OrderService.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Cart is Empty")
public class CartIsEmpty extends Exception {
}
