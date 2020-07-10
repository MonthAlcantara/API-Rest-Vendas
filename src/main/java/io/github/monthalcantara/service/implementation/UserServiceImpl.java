package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.model.UserLogin;
import io.github.monthalcantara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String[] roles = userLogin.isAdmin() ?
                new String[]{"USER", "ADMIN"} : new String[]{"USER"};
        return User.builder()
                .username(userLogin.getLogin())
                .password(userLogin.getPassword())
                .roles(roles)
                .build();
    }

    public UserLogin save(UserLogin userLogin) {
        String password = passwordEncoder.encode(userLogin.getPassword());
        userLogin.setPassword(password);
        return repository.save(userLogin);
    }
}
