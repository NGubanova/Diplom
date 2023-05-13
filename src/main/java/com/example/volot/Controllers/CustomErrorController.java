package com.example.volot.Controllers;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        // Логика обработки ошибок
        ModelAndView modelAndView = new ModelAndView("forAll/error-page");
        modelAndView.addObject("errorMessage", "Oops! Something went wrong.");
        return modelAndView;
    }
}
