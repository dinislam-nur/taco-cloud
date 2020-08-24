package com.first.springboot.demo.data.tacos;

import com.first.springboot.demo.domains.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
