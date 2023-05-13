package com.example.volot.Controllers;

import com.example.volot.Models.Role;
import com.example.volot.Models.User;
import com.example.volot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String reg(User user) {
        return ("forAll/registration");
    }

    @PostMapping("/registration")
    public String reg(@Valid User user, Model model, BindingResult result) {

        if (result.hasErrors())
            return ("forAll/registration");
        else if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Эл. почта занята!");
            return ("forAll/registration");
        } else if (user.getPhone() == null || user.getPhone().equals("")) {
            model.addAttribute("error", "Введите номер телефона по маске +7(___)-___-__-__");
            return ("forAll/registration");
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ("redirect:/login");
    }
}
