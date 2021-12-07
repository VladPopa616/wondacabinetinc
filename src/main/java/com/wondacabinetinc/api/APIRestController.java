package com.wondacabinetinc.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class APIRestController {
    @RequestMapping("/")
    public String home(){
        return "Hello Docker World";
    }
}
