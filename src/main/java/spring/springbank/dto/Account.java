package spring.springbank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor //Creates constructor with all arguments
@NoArgsConstructor  //
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double balance;

    private String currency;



    public void addMoney(double cash) {
        this.balance += cash;
    }

    public void withdrawMoney(double cash) {
        if (balance < cash) {
            System.out.println("Not Enough funds");
        } else {
            balance -= cash;
        }
    }
}
