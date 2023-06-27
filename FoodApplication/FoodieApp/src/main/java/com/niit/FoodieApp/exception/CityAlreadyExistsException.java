package com.niit.FoodieApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "City already exists")
public class CityAlreadyExistsException extends Exception{

}
