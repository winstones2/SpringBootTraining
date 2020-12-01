package com.tangyu.springboottraining.controller;

import com.tangyu.springboottraining.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    public String home(User user, Model model) {

        model.addAttribute("name", user.getName());
        model.addAttribute("id", user.getId());
        return "intro";
    }

    @RequestMapping("/")
    public ModelAndView home(@RequestParam(value = "count", defaultValue = "1") int count) {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("count", count + 1);
        return mav;
    }

}
