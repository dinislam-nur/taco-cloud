package com.first.springboot.demo.data.users;


import com.first.springboot.demo.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
