package spring.springbank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor //Creates constructor with all arguments
@NoArgsConstructor  //
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private String nationality;

    private String username;

    private String password;

    private Boolean loggedIn = false;

    private String roles;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Account account;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    private Login login;






}
