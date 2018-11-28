package spring.springbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.springbank.beans.InterestThread;

import javax.annotation.PostConstruct;

@Service
public class DepositThread extends Thread {

    @Autowired
    InterestThread interestThread;

    @PostConstruct
    public void runStuff() {

        new Thread(() -> interestThread.run()).start();

    }
}
