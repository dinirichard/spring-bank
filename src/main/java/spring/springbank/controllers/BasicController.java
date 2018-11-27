package spring.springbank.controllers;


import org.springframework.web.bind.annotation.*;
import spring.springbank.dto.Users;
import spring.springbank.repository.AccountRepo;
import spring.springbank.repository.UserRepo;

import javax.websocket.server.PathParam;

@RequestMapping(path = "/")
@RestController
public class BasicController {

    UserRepo userRepo;
    AccountRepo accountRepo;


    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public void addNewUser(@RequestBody Users user) {
        accountRepo.save(user.getAccount());
        userRepo.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String login(@PathParam("username") String username, @PathParam("password") String password) {
        Users user = userRepo.findByUsernameAndPassword(username, password);

        if (user == null) {
            return "Username or Password is Incorrect";

        } else {
            user.setLoggedIn(true);
            userRepo.save(user);
            return "You've Logged In";
        }

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
