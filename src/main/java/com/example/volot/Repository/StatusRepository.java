package com.example.volot.Repository;

import com.example.volot.Models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Status findByName(String name);
}
