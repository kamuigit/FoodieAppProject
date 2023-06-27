package com.niit.OrderService.controller;

import com.niit.OrderService.domain.Menu;
import com.niit.OrderService.domain.Order;
import com.niit.OrderService.domain.UserOrderAdded;
import com.niit.OrderService.domain.UserOrderPlaced;
import com.niit.OrderService.exception.CartIsEmpty;
import com.niit.OrderService.exception.MenuAlreadyExists;
import com.niit.OrderService.exception.OrderDoesNotExist;
import com.niit.OrderService.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/order/v1")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;

    @PostMapping("/UserOrderAdded")
    public ResponseEntity orderAdded(@RequestBody UserOrderAdded userOrderAdded) throws MenuAlreadyExists {
        return new ResponseEntity<>(iOrderService.orderAdded(userOrderAdded), HttpStatus.CREATED);
    }

    @PostMapping("/UserOrderPlaced")
    public ResponseEntity orderPlaced(@RequestBody UserOrderPlaced userOrderPlaced){
        return new ResponseEntity<>(iOrderService.orderPlaced(userOrderPlaced),HttpStatus.OK);
    }
    @GetMapping("/getDataByEmail/{email}")
    public ResponseEntity getDataByEmail(@PathVariable String email) throws CartIsEmpty {
        return new ResponseEntity<>(iOrderService.getDataByEmail(email),HttpStatus.OK);
    }
    @GetMapping("/getAllOrdersByEmail/{email}")
    public ResponseEntity getAllOrders(@PathVariable String email) throws OrderDoesNotExist {
        return new ResponseEntity<>(iOrderService.getAllOrders(email),HttpStatus.OK);
    }
    @GetMapping("/getCurrentOrderByEmail/{email}")
    public ResponseEntity getCurrentOrder(@PathVariable String email){
        return new ResponseEntity<>(iOrderService.getCurrentOrder(email),HttpStatus.OK);
    }
    @PostMapping("/deleteItem/{email}")
    public ResponseEntity<?> deleteItem(@PathVariable String email, @RequestBody Menu menu){
        return new ResponseEntity<>( iOrderService.deleteMenu(email,menu), HttpStatus.OK);
    }
    //      http://localhost:8082/api/order/v1/clearCart/{email}
    @DeleteMapping ("/clearCart/{email}")
    public ResponseEntity<?> delete(@PathVariable String email){
        return new ResponseEntity<>( iOrderService.clearCart(email), HttpStatus.OK);
    }

    @PostMapping("/placeOrderNow/{email}")
    public ResponseEntity<?> placeOrder(@PathVariable String email ,@RequestBody Order order) throws OrderDoesNotExist {
        return new ResponseEntity<>(iOrderService.placeNewOrder(email,order),HttpStatus.ACCEPTED);
    }
}
