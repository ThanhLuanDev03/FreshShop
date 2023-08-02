package com.freshshop.controller.UserController;

import com.freshshop.model.Customer;
import com.freshshop.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@Controller
public class RegisterController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/register")
    public String getSignup(Model model, @Valid @ModelAttribute("customer") Customer customer, Errors errors){
        if(errors.hasErrors()){
            log.error("Validation fail: " + errors.toString());

//            boolean emailMismatch = errors.getAllErrors().stream()
//                    .anyMatch(error -> error.getDefaultMessage().equals("email do not match"));
//            boolean passwordMismatch = errors.getAllErrors().stream()
//                    .anyMatch(error -> error.getDefaultMessage().equals("password do not match"));
//            if(emailMismatch){
//                model.addAttribute("emailMismatch", "Email do not match");
//            }
//            if(passwordMismatch){
//                model.addAttribute("passwordMismatch", "Password do not match");
//            }
            return "register.html";
        }
        if(!customer.getEmail().equals(customer.getConfirmEmail())){
            model.addAttribute("emailMismatch", "Email do not match");
            return "User/register.html";
        }
        if(!customer.getPwd().equals(customer.getConfirmPwd())){
            model.addAttribute("passwordMismatch", "Password do not match");
            return "User/register.html";

        }
        boolean isCreated = customerService.createNewPerson(customer);
        if(isCreated ){
            return "redirect:/login?register=true";
        }
        else {
        model.addAttribute("message", "Can't register!");
            return "User/register.html";

        }
    }
}
