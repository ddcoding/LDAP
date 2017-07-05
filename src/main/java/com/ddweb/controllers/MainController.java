package com.ddweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jakdwo on 05.07.2017.
 */
@Controller
public class MainController {

    @RequestMapping("/home")
    public String home(){
        System.out.print("dupa");
        return "views/index.html";
    }
}

