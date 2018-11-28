package spring.springbank.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.springbank.dto.Users;
import spring.springbank.repository.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public UserDetails loadUserByUsername(String usssername) throws UsernameNotFoundException {
        Users userFromDb = userRepo.findByUsername(usssername);

        if (userFromDb == null)
            return null;

        String role = userFromDb.getRoles();
        userFromDb.setLoggedIn(true);
        userRepo.save(userFromDb);

        if (role.equals("ADMIN")) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(usssername)
                    .password(userFromDb.getPassword())
                    .roles(role)
                    .roles("USER")
                    .build();
        }

        //TODO WHat does this do?
        return org.springframework.security.core.userdetails.User.builder()
                .username(usssername)
                .password(userFromDb.getPassword())
                .roles(role)
                .build();

    }
}
