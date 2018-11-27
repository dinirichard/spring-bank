package spring.springbank.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.springbank.dto.Account;
import spring.springbank.dto.Users;
import spring.springbank.repository.AccountRepo;
import spring.springbank.repository.LoginRepo;
import spring.springbank.repository.UserRepo;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController  {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    LoginRepo loginRepo;



    @RequestMapping(method = RequestMethod.GET, path = "/getSum")
    public double getSum(@PathParam("nationality") String nationality) {

        double Sum = 0.0;

        for (Users user : userRepo.findAllByNationality(nationality)) {
            Sum += user.getAccount().getBalance();
        }
        return Sum;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public void addNewUser(@RequestBody Users user) {
        accountRepo.save(user.getAccount());
        userRepo.save(user);
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

    @RequestMapping(method = RequestMethod.POST, path = "/withdraw")
    public String withdraw(@PathParam("amount") double amount, @PathParam("name") String name) {

        String yes = "Not logged in";
        Users users = userRepo.findByName(name);
        if (users.getLoggedIn()) {
            users.getAccount().withdrawMoney(amount);
            userRepo.save(users);
            yes = "Withdrawn";
        }

        return yes + "\nBalance = " + users.getAccount().getBalance();
    }




    public void deposit() {


        Iterable<Account> allAccounts = accountRepo.findAll();

        for (Account account : allAccounts) {

            account.setBalance(account.getBalance() * 1.10);
            accountRepo.save(account);
        }
    }





//    public void run() {
//
//        Thread thread = new Thread(() -> {
//            try {
//                while (true) {
//                    Thread.sleep(1000);
//
//
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }


}

