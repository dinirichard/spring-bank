package spring.springbank.repository;

import org.springframework.data.repository.CrudRepository;
import spring.springbank.dto.Login;

public interface LoginRepo extends CrudRepository<Login, Integer> {


}

