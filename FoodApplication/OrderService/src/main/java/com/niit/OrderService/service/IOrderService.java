package com.niit.OrderService.service;

import com.niit.OrderService.domain.Menu;
import com.niit.OrderService.domain.Order;
import com.niit.OrderService.domain.UserOrderAdded;
import com.niit.OrderService.domain.UserOrderPlaced;
import com.niit.OrderService.exception.CartIsEmpty;
import com.niit.OrderService.exception.MenuAlreadyExists;
import com.niit.OrderService.exception.OrderDoesNotExist;

import java.util.List;

public interface IOrderService {
    public UserOrderAdded orderAdded(UserOrderAdded userOrderAdded) throws MenuAlreadyExists;
    public UserOrderPlaced orderPlaced(UserOrderPlaced userOrderPlaced);
    public UserOrderAdded getDataByEmail(String email) throws CartIsEmpty;
    public List<Order> getAllOrders(String email) throws OrderDoesNotExist;
    public Order getCurrentOrder(String email);
    public UserOrderAdded deleteMenu(String email,Menu menu);
    public boolean clearCart(String email);
    public boolean placeNewOrder(String email,Order order) throws OrderDoesNotExist;
}
