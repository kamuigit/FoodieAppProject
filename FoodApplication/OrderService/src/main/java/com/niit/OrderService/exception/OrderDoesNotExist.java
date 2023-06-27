package com.niit.OrderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Order Does Not Exist for the email")
public class OrderDoesNotExist extends Exception{
}
