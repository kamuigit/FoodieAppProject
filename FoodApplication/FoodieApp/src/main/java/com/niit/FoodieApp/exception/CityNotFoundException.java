package com.niit.FoodieApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "City Not Found")
public class CityNotFoundException extends Exception {
}
