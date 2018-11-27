package spring.springbank.repository;

import org.springframework.data.repository.CrudRepository;
import spring.springbank.dto.Users;

public interface UserRepo extends CrudRepository<Users, Integer> {

    Users findByUsername(String username);

    Iterable<Users> findAllByName(String name);
    Users findByName(String name);

    Iterable<Users> findAllByNationality(String Nationality);


    Users findByUsernameAndPassword(String username, String password);

    Users findByAccount_Id(Integer Id);




}
