package com.example.volot.Repository;

import com.example.volot.Models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long Id);
    List<Order> findOrdersByVolotid(String volotid);
    Order getFirstByVolotid(String Volotid);
    Iterable<Order> findByVolotid(String Volotid);

    @Query("select distinct volotid from Order where user.id = ?1")
    ArrayList<String> getByVolotid(Long user);
}
