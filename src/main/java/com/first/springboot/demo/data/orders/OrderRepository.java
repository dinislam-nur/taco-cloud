package com.first.springboot.demo.data.orders;

import com.first.springboot.demo.domains.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
