package com.sa.java17gda.yoyo_allegro.controller;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.authentication.AuthenticationProvider;
@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex() {
        return "index";

}

@GetMapping("/allProducts")
    public String getAllProducts() {
        return "allProducts";
    }
}
