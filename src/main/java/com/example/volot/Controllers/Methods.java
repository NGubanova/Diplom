package com.example.volot.Controllers;

import com.example.volot.Models.*;
import com.example.volot.Repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Methods {
    public User checkAuth(UserRepository userRepository) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return user;
    }

    Iterable<Category> listCategory(CategoryRepository categoryRepository) {
        return categoryRepository.findAll();
    }
    Iterable<Feedback> listFeedback(FeedbackRepository feedbackRepository) {
        return feedbackRepository.findAll();
    }
    Iterable<Material> listMaterial(MaterialRepository materialRepository) {
        return materialRepository.findAll();
    }
    Iterable<Order> listOrder(OrderRepository orderRepository) {
        return orderRepository.findAll();
    }
    Iterable<Product> listProduct(ProductRepository productRepository) {
        return productRepository.findAll();
    }
    Iterable<Subcategory> listSubcategory(SubcategoryRepository subcategoryRepository) {
        return subcategoryRepository.findAll();
    }
    Iterable<User> listUser(UserRepository userRepository) {
        return userRepository.findAll();
    }

}
