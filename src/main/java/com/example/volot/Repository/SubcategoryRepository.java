package com.example.volot.Repository;

import com.example.volot.Models.Subcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {

    List<Subcategory> findByCategoryId(Long id);
    List<Subcategory> findByCategoryName(String name);
}
