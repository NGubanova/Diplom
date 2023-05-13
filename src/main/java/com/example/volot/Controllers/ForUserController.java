package com.example.volot.Controllers;

import com.example.volot.Models.Feedback;
import com.example.volot.Models.Order;
import com.example.volot.Models.Product;
import com.example.volot.Models.User;
import com.example.volot.Repository.FeedbackRepository;
import com.example.volot.Repository.OrderRepository;
import com.example.volot.Repository.ProductRepository;
import com.example.volot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
@PreAuthorize("hasAuthority('USER')")
public class ForUserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    FeedbackRepository feedbackRepository;
    Methods methods = new Methods();

    @GetMapping("")
    public String profile(User user, Model model) {
        user = methods.checkAuth(userRepository);
        model.addAttribute("user", user);
        ArrayList<String> orderList = orderRepository.getByVolotid(user.getId());
        model.addAttribute("order", convertArrayToList(orderList));
        return ("forUser/profile");
    }

    @GetMapping("/edit/{id}")
    public String profileEdit(User user, Model model, @PathVariable long id) {
        user = methods.checkAuth(userRepository);
        model.addAttribute("user", user);
        return ("forUser/editUser");
    }

    @PostMapping("/edit/{id}")
    public String profileEditPost(@Valid User user, Model model) {
        User user1 = userRepository.findById(user.getId()).orElseThrow();
        user1.setName(user.getName());
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        userRepository.save(user1);
        return "redirect:/profile";
    }

    @GetMapping("/delete/{id}")
    public String profileDelete(User user, Model model) {
        user = userRepository.findById(user.getId()).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/favorite")
    public String favorite(User user, Model model) {
        user = methods.checkAuth(userRepository);
        if (user == null) {
            return "redirect:/login";
        }
        Iterable<Product> products = productRepository.findProductsByUsersId(user.getId());
        model.addAttribute("products", products);
        return ("forUser/favorite");
    }

    @GetMapping("/favorite/{id}")
    public String deleteProduct(User user, Model model, @PathVariable long id) {
        user = methods.checkAuth(userRepository);

        user.removeProduct(productRepository.findById(id).orElseThrow());
        userRepository.save(user);
        Iterable<Product> products = productRepository.findProductsByUsersId(user.getId());
        model.addAttribute("products", products);
        return "redirect:/profile/favorite";
    }

    @GetMapping("/order/{Volotid}")
    public String OrderPage(Feedback feedback, Model model,
                            @PathVariable String Volotid) {
        methods.checkAuth(userRepository);

        Order orders = orderRepository.getFirstByVolotid(Volotid);
        model.addAttribute("orders", orders);

        Iterable<Order> productList = orderRepository.findByVolotid(Volotid);
        model.addAttribute("product", productList);
        return "/forUser/detailOrder";
    }

    @PostMapping("/order/{Volotid}")
    public String addFeedback(Feedback feedback, @RequestParam Long product, Model model,
                              @PathVariable String Volotid) {

        Order orders = orderRepository.getFirstByVolotid(Volotid);
        model.addAttribute("orders", orders);

        Iterable<Order> productList = orderRepository.findByVolotid(Volotid);
        model.addAttribute("product", productList);

        feedback.setUser(methods.checkAuth(userRepository));
        feedback.setProduct(productRepository.findById(product).orElseThrow());
        feedbackRepository.save(feedback);
        return "/forUser/detailOrder";
    }

    public List<Order> convertArrayToList(List<String> orders) {
        List<Order> orderList = new ArrayList<>();
        for (String string : orders) {
            orderList.add(orderRepository.getFirstByVolotid(string));
        }
        return orderList;
    }
}
