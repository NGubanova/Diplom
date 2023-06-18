package com.example.volot.Repository;
import com.example.volot.Models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {


    List<Product> findBySubcategoryCategoryNameAndStatusTrue(String categories);
    List<Product> findProductsBySubcategoryIdAndStatusTrue(Long Id);

    List<Product> findProductsByUsersId(Long id);
    List<Product> findProductsByStatusTrue();
    List<Product> findProductsByNameContainsAndStatusTrue(String name);

}
