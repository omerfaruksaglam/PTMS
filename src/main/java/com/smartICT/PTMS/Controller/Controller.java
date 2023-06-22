package com.smartICT.PTMS.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class Controller {

    @GetMapping("/index")
    public String sayHello(){
        return "Hello";
    }
}
