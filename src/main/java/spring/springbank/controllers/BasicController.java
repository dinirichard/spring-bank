package spring.springbank.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.springbank.dto.Users;
import spring.springbank.repository.AccountRepo;
import spring.springbank.repository.UserRepo;

import javax.websocket.server.PathParam;

@RequestMapping(path = "/")
@RestController
public class BasicController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;


    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public void addNewUser(@RequestBody Users user) {
        accountRepo.save(user.getAccount());
        userRepo.save(user);
    }


    @GetMapping
    public String hello() {
        return "Welcome!";
    }

    @GetMapping(path = "/protected")
    public String helloFromProtectedResource() {
        return "Here you are safe!";
    }
}
