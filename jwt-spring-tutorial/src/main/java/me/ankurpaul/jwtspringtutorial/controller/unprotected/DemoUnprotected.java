package me.ankurpaul.jwtspringtutorial.controller.unprotected;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-unprotected-controller")
public class DemoUnprotected {
    @GetMapping
    public String sayHello() {
        return "Hello World, this is an unprotected route and does not require jwt authentication!";
    }
}
