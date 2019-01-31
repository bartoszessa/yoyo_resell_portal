package com.sa.java17gda.yoyo_allegro.controller;

import com.sa.java17gda.yoyo_allegro.model.AppUser;
import com.sa.java17gda.yoyo_allegro.model.dto.request.RegisterUserRequest;
import com.sa.java17gda.yoyo_allegro.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthorizationController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "authorization/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {

        model.addAttribute("form", new RegisterUserRequest());

        return "authorization/register";
    }

    @PostMapping("/register")
    public String sendRegister(Model model, RegisterUserRequest request) {

        Optional<AppUser> appUserOptional = appUserService.register(request);
        if (appUserOptional.isPresent()) {
            return "redirect:/login";
        }
        model.addAttribute("message", "Could not register, try different Username.");
        model.addAttribute("form", request);

        return "authorization/register";
    }
}
