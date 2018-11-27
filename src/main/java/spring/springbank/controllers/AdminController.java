package spring.springbank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.springbank.dto.Account;
import spring.springbank.dto.Users;
import spring.springbank.repository.AccountRepo;
import spring.springbank.repository.UserRepo;

import javax.websocket.server.PathParam;

@RequestMapping(path = "/admin")
@RestController
public class AdminController {

    UserRepo userRepo;
    AccountRepo accountRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/allByName")
    public Iterable<Users> getByName(@PathParam("name") String name) {

        return userRepo.findAllByName(name);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public String deleteUser(@PathParam("name")String name, @PathParam("deleteID")Integer deleteID) {
        Users admin = userRepo.findByName(name);

        if (admin != null) {
            if (admin.getLoggedIn() && admin.getRoles().equals("ADMIN")) {
                Users badUser = userRepo.findByAccount_Id(deleteID);
                Account badAccount = accountRepo.findAccountById(deleteID);
                userRepo.delete(badUser);
                accountRepo.delete(badAccount);
                return "User: " + deleteID + "has been deleted";
            }
            else {
                return "404";
            }
        }
        else {
            return "404";
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/avgAge")
    public double avgAge() {
        Iterable<Users> allUsers = userRepo.findAll();
        Long numOfUsers = userRepo.count();

        double ageSum = 0.0;
        for (Users user : allUsers) {
            ageSum += user.getAge();
        }
        return (ageSum / numOfUsers);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/logout")
    public String logout(@PathParam("username") String username, @PathParam("password") String password) {
        Users user = userRepo.findByUsernameAndPassword(username, password);


        if (user.getLoggedIn()) {
            user.setLoggedIn(false);
            userRepo.save(user);
            return "You've Logged Out";
        } else {
            return "Not logged In";
        }

    }
}
