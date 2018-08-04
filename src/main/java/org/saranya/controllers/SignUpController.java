package org.saranya.controllers;


import org.saranya.models.forms.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class SignUpController {
    @RequestMapping(value="/signup",method=RequestMethod.GET)
    public String getSignUpForm(){
        return "signup";
    }
    @RequestMapping(value="/signup",method=RequestMethod.POST)
    public String signup(@ModelAttribute(name="signupform")SignUpForm signupform, Model model){
        String username = signupform.getUsername();
        String password = signupform.getPassword();
        String repassword = signupform.getRepassword();

        if("admin".equals(username) && "admin".equals(password) && "admin".equals(repassword)){
            return "home";
        }
        model.addAttribute("invalidCredentials",true);
        return "signup";
    }
}
