package com.kjslab.chat.view.controller;

import com.kjslab.chat.view.repository.Address;
import com.kjslab.chat.view.repository.EmailAddress;
import com.kjslab.chat.view.repository.MongoDbUserRepository;
import com.kjslab.chat.view.repository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kimjinsam on 2016. 8. 13..
 */

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    protected MongoDbUserRepository userRepository;

    @RequestMapping("/")
    public String root() {

        EmailAddress emailAddress = new EmailAddress("alicia@keys.com");

        User user = null;
        try {
            user = userRepository.findByEmailAddress(emailAddress);
            if(user == null){
                User alicia = new User("Alicia", "Keys");
                alicia.setEmailAddress(emailAddress);
                alicia.setPassword("alicia");
                alicia.add(new Address("27 Broadway", "New York", "United States"));

                User result = userRepository.save(alicia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(user);
        return "/home";
    }

    @RequestMapping("/home")
    public String home() {

        return "/home";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "/hello";
    }
}
