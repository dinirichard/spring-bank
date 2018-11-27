package spring.springbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.springbank.controllers.UserController;

@SpringBootApplication
public class SpringBankApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringBankApplication.class, args);

//        ApplicationContext ctx = new AnnotationConfigApplicationContext(DepositThread.class);
//
//        DepositThread printThread1 = (DepositThread) ctx.getBean("printThread");
//        printThread1.setName("Thread 1");
    }


}
