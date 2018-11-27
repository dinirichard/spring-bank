package spring.springbank.repository;

import org.springframework.data.repository.CrudRepository;
import spring.springbank.dto.Account;
import spring.springbank.dto.Currency;

import java.util.Optional;

public interface AccountRepo extends CrudRepository<Account, Integer> {

    Iterable<Account> findAllByCurrency(Currency currency);

    Account findAccountById(Integer Id);



}
