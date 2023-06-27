package com.niit.OrderService.service;

import com.niit.OrderService.domain.Menu;
import com.niit.OrderService.domain.Order;
import com.niit.OrderService.domain.UserOrderAdded;
import com.niit.OrderService.domain.UserOrderPlaced;
import com.niit.OrderService.exception.CartIsEmpty;
import com.niit.OrderService.exception.MenuAlreadyExists;
import com.niit.OrderService.exception.OrderDoesNotExist;
import com.niit.OrderService.repository.OrderRepository;
import com.niit.OrderService.repository.UserOrderAddedRepository;
import com.niit.OrderService.repository.UserOrderPlacedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService{
    private OrderRepository orderRepository;
   private UserOrderAddedRepository userOrderAddedRepository;
    @Autowired
   private UserOrderPlacedRepository userOrderPlacedRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserOrderAddedRepository userOrderAddedRepository, UserOrderPlacedRepository userOrderPlacedRepository) {
        this.orderRepository = orderRepository;
        this.userOrderAddedRepository = userOrderAddedRepository;
        this.userOrderPlacedRepository = userOrderPlacedRepository;
    }


    @Override
    public UserOrderAdded orderAdded(UserOrderAdded userOrderAdded) throws MenuAlreadyExists {
        UserOrderAdded userOrderAdded1 = new UserOrderAdded();
        if(!userOrderAddedRepository.findById(userOrderAdded.getEmail()).isPresent()){
            if(userOrderPlacedRepository.findById(userOrderAdded.getEmail()).isEmpty()){
                UserOrderPlaced userOrderPlaced = new UserOrderPlaced(userOrderAdded.getEmail(),null);
                userOrderPlacedRepository.save(userOrderPlaced);
            }
            return userOrderAddedRepository.save(userOrderAdded);
        }
        else {
            int c=0;
            for (Menu m:userOrderAddedRepository.findById(userOrderAdded.getEmail()).get().getMenuList()) {
                if(m.getItemName().equals(userOrderAdded.getMenuList().get(0).getItemName())){
                    c=1;
                }
            }
            if(c==0){
                userOrderAdded1 = userOrderAddedRepository.findById(userOrderAdded.getEmail()).get();
                List<Menu> menus =userOrderAdded1.getMenuList();
                menus.add(userOrderAdded.getMenuList().get(0));
                userOrderAdded1.setMenuList(menus);
                return userOrderAddedRepository.save(userOrderAdded1);
            } else {
                throw new MenuAlreadyExists();
            }
        }
    }

    @Override
    public UserOrderPlaced orderPlaced(UserOrderPlaced userOrderPlaced) {
        UserOrderPlaced userOrderPlaced1 = null;
        if(userOrderPlacedRepository.findById(userOrderPlaced.getEmail()).isPresent()){
            userOrderPlaced1 = userOrderPlacedRepository.findById(userOrderPlaced.getEmail()).get();
            List<Order> list = userOrderPlaced1.getOrderList();
            list.add(userOrderPlaced.getOrderList().get(0));
            userOrderPlaced1.setOrderList(list);
        }
        else {
            userOrderPlaced1 = userOrderPlaced;
        }
        return userOrderPlacedRepository.save(userOrderPlaced1);
    }

    @Override
    public UserOrderAdded getDataByEmail(String email) throws CartIsEmpty {
        if(userOrderAddedRepository.findById(email).isPresent()) {
            UserOrderAdded userOrderAdded = userOrderAddedRepository.findById(email).get();
            return userOrderAdded;
        }
        else{
            throw new CartIsEmpty();
        }

    }

    @Override
    public List<Order> getAllOrders(String email) throws OrderDoesNotExist {
        if (userOrderPlacedRepository.findById(email).isPresent()) {
            return userOrderPlacedRepository.findById(email).get().getOrderList();
        }
        else{
            throw new OrderDoesNotExist();
        }
    }
    @Override
    public Order getCurrentOrder(String email) {
        UserOrderPlaced user =  userOrderPlacedRepository.findById(email).get();
        int index = user.getOrderList().size() - 1;
        return user.getOrderList().get(index);
    }

    @Override
    public UserOrderAdded deleteMenu(String email, Menu menu) {
        UserOrderAdded userOrderAdded1= userOrderAddedRepository.findById(email).get();
        List<Menu> menuList=userOrderAdded1.getMenuList();
        Menu menu1;
        Iterator<Menu> i = menuList.iterator();

        while (i.hasNext()){
            menu1=i.next();
            if(menu1.getItemName().equals(menu.getItemName()) && menu1.getQty().equals(menu.getQty())){
                i.remove();
            }
        }
        userOrderAdded1.setMenuList(menuList);
        return userOrderAddedRepository.save(userOrderAdded1);
    }

    @Override
    public boolean clearCart(String email) {
        userOrderAddedRepository.deleteById(email);
        return true;
    }
    @Override//my method
    public boolean placeNewOrder(String email,Order order) throws OrderDoesNotExist {
        if(userOrderPlacedRepository.findById(email).get().getOrderList() == null){
            List<Order> orderList = new ArrayList<>();
            orderList.add(order);
            UserOrderPlaced userOrderPlaced = new UserOrderPlaced(email,orderList);
            userOrderPlacedRepository.save(userOrderPlaced);
            return true;
        }
        else if(userOrderPlacedRepository.findById(email).isEmpty()) {
            throw new OrderDoesNotExist();
        }
        else{
            List<Order> orderList = userOrderPlacedRepository.findById(email).get().getOrderList();
            orderList.add(order);
            UserOrderPlaced userOrderPlaced = new UserOrderPlaced(email,orderList);
            userOrderPlacedRepository.save(userOrderPlaced);
            return true;
        }
    }
}
