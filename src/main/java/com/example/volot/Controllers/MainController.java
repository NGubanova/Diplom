package com.example.volot.Controllers;

import com.example.volot.Models.*;
import com.example.volot.Repository.*;
import com.example.volot.Service.HardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    FeedbackRepository feedbackRepository;

    //    @Autowired
//    MailSender mailSender;
    private final HardService hardService;

    List ListId = new ArrayList<Long>();

    List ListAmount = new ArrayList<>();

    Methods methods = new Methods();

    public MainController(HardService hardService) {
        this.hardService = hardService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        return "forAll/main";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "forAll/contact";
    }

    @GetMapping("/")
    public String aboutPage(Model model) {
        return "forAll/about";
    }

    @GetMapping("/catalog")
    public String catalogPage(Model model) {
        model.addAttribute("categories", methods.listCategory(categoryRepository));
        model.addAttribute("subcategories", subcategoryRepository.findByCategoryId(1L));
        List<Product> productList = productRepository.findProductsByStatusTrue();
        model.addAttribute("product", productList);
        return "forAll/catalog";
    }

    @GetMapping("/catalog/like/{id}")
    public String addLike(@PathVariable long id, Model model) {
        User user = methods.checkAuth(userRepository);
        if (user == null) {
            return "redirect:/login";
        }
        Product product = productRepository.findById(id).orElseThrow();
        user.getProducts().add(product);
        userRepository.save(user);
        return "redirect:/catalog";
    }

    @GetMapping("/catalog/filter")
    public String Filter(@RequestParam String categories,
                         Model model) {
        model.addAttribute("categories", methods.listCategory(categoryRepository));
        model.addAttribute("subcategories", subcategoryRepository.findByCategoryName(categories));
        List<Product> products = productRepository.findBySubcategoryNameAndStatusTrue(categories);
        model.addAttribute("product", products);
        return "forAll/catalog";
    }

    @GetMapping("/catalog/subfilter")
    public String FilterSub(@RequestParam Long subcategories,
                         Model model) {
        model.addAttribute("categories", methods.listCategory(categoryRepository));
        model.addAttribute("subcategories", subcategoryRepository.findByCategoryId(1L));
        List<Product> products = productRepository.findProductsBySubcategoryIdAndStatusTrue(subcategories);
        model.addAttribute("product", products);
        return "forAll/catalog";
    }

    @GetMapping("/catalog/search")
    public String Search(@RequestParam String search,
                         Model model) {
        model.addAttribute("categories", methods.listCategory(categoryRepository));
        List<Product> productList = productRepository.findProductsByNameContainsAndStatusTrue(search);
        model.addAttribute("product", productList);
        return "/forAll/catalog";
    }

    @GetMapping("catalog/{id}")
    public String productDetails(Model model,
                                 @PathVariable long id) {
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        List<Feedback> feedbacks = feedbackRepository.findFeedbackByProductId(id);
        model.addAttribute("feedback", feedbacks);
        return ("/forAll/productDetails");
    }

    @GetMapping("catalog/{id}/add")
    public String productDetails(Product product, Model model, HttpServletRequest request,
                                 @PathVariable long id) {
        User user = methods.checkAuth(userRepository);
        if (user == null) {
            return "redirect:/login";
        }
        ListId.add(id);
        if (ListId.size() == 1) {
            hardService.get(ListId);
            request.setAttribute("listId", ListId);
        } else
            hardService.update(ListId);
        request.setAttribute("listId", ListId);
        product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return ("redirect:/catalog");
    }

    @GetMapping("catalog/{id}/addFavorite")
    public String addFavorite(Product product, Model model, HttpServletRequest request,
                                 @PathVariable long id) {
        User user = methods.checkAuth(userRepository);
        ListId.add(id);
        if (ListId.size() == 1) {
            hardService.get(ListId);
            request.setAttribute("listId", ListId);
        } else
//            hardService.update(ListId);
        request.setAttribute("listId", ListId);
        product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return ("redirect:/profile/favorite");
    }

    @GetMapping("/cart")
    public String cart(Order order, Model model) {
        Iterable<Product> listProduct = hardService.set();
        model.addAttribute("listProduct", listProduct);

        return "/forAll/cart";
    }

    @GetMapping("/cart/{id}")
    public String deleteProduct(Order order, Model model, @PathVariable long id) {
        ListId.remove(id);
        hardService.update(ListId);
        Iterable<Product> listProduct = hardService.set();
        model.addAttribute("listProduct", listProduct);

        return "redirect:/cart";
    }

    @PostMapping("/cart")
    public String addOrder(@Valid Order order,
                           BindingResult result, Model model) {

        User user = methods.checkAuth(userRepository);
        if (user == null) {
            return "redirect:/login";
        }
        Iterable<Product> listProduct = hardService.set();
        model.addAttribute("listProduct", listProduct);

        if (result.hasErrors())
            return ("forAll/cart");

        Order orderMain = new Order();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MM y HH:mm");
        String dateTime = dateFormat.format(date);

        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-y");
        String dateOrder = dt.format(date);

        for (Product product1 : listProduct) {
            orderMain.setId(user.getId() + "-" + product1.getId());
            orderMain.setVolotid(user.getId() + "-" + dateOrder);
            orderMain.setUser(user);
            orderMain.setProduct(product1);
            orderMain.setDateTime(dateTime);
            orderMain.setAddress(order.getAddress());
            orderMain.setContract("Не заключен");
            orderMain.setStatus(statusRepository.findById(1L).orElseThrow());
            orderRepository.save(orderMain);
        }

//        List<Product> a = iterableToList(listProduct);
//        String order = a.stream()
//                .map(n-> String.valueOf(n.getName()))
//                .collect(Collectors.joining("\n"));
//
//        String message = String.format(
//                "Новый заказ \nВ него входят:\n"+order
//                        +"\n\nИмя пользователя: "+user.getName()
//                        +"\nНомер телефона: "+user.getPhone()
//                        +"\nПочта: "+user.getUsername()
//                        +"\nАдрес: "+user.getAddress()
//        );
//
//        mailSender.send("loshadka.3@yandex.ru", "Новый заказ",message);

        hardService.evict();
        listProduct = null;
        return "redirect:/";
    }

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> collection = new ArrayList<T>();
        for (T e : iterable) {
            collection.add(e);
        }
        return collection;
    }
}
