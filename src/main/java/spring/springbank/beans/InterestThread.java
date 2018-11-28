package spring.springbank.beans;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.springbank.dto.Account;
import spring.springbank.dto.Users;
import spring.springbank.repository.AccountRepo;
import spring.springbank.repository.UserRepo;


@Component
public class InterestThread implements Runnable {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @Override
    @Scheduled(fixedDelay = 10000)
    public synchronized void run() {
        while (true) {


            Iterable<Users> users = userRepo.findAll();
            for (Users user : users) {
                Account account = user.getAccount();
                account.setBalance(account.getBalance() * (1d + (/*account.getInterestRate()*/ 10 / 100d)));
                accountRepo.save(account);
            }

            try {

                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
