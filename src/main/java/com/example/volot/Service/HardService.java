package com.example.volot.Service;

import com.example.volot.Models.Product;
import com.example.volot.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class HardService {

    Iterable<Product> listProduct;
    @Autowired
    ProductRepository productRepository;
    @Cacheable(cacheNames = {"ListId"})
    public Iterable<Product> get(Iterable<Long> IDs){
        listProduct = productRepository.findAllById(IDs);
        return listProduct;
    }

    public Iterable<Product> set(){
        return listProduct;
    }

    @CachePut(cacheNames = {"ListId"})
    public Iterable<Product> update(Iterable<Long> IDs){
        listProduct = productRepository.findAllById(IDs);
        return listProduct;
    }

    @CacheEvict("ListId")
    public Iterable<Product> evict(){
        listProduct = null;
        return listProduct;}
}
