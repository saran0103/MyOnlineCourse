package org.saranya.controllers;

import org.saranya.models.forms.LoginForm;
import org.saranya.models.forms.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String getLoginForm(){
        return "login";
    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(@ModelAttribute(name="loginform")LoginForm loginform, Model model){
        String username = loginform.getUsername();
        String password = loginform.getPassword();


        if("admin".equals(username) && "admin".equals(password)){
            return "home";
        }
        model.addAttribute("invalidCredentials",true);
        return "login";
    }
}
