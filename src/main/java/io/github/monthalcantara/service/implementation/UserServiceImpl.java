package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.dto.request.UserLoginDTO;
import io.github.monthalcantara.dto.response.UserLoginResponseDTO;
import io.github.monthalcantara.exception.InvalidPasswordException;
import io.github.monthalcantara.mappers.UserMapper;
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
    private UserMapper userMapper;

    @Autowired
    private UserRepository repository;

    public UserDetails authenticate(UserLogin userLogin){
        UserDetails userDetails = loadUserByUsername(userLogin.getLogin());
        boolean isEqual = passwordEncoder.matches(userLogin.getPassword(), userDetails.getPassword());
        if(isEqual){
            return userDetails;
        }
        throw new InvalidPasswordException();
    }

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

    public UserLoginResponseDTO save(UserLoginDTO userLoginDTO) {
        UserLogin userLogin = userMapper.userLoginDTOToUserLogin(userLoginDTO);
        String password = passwordEncoder.encode(userLogin.getPassword());
        userLogin.setPassword(password);
        return userMapper.userLoginToUserLoginResponseDTO(repository.save(userLogin));
    }
}
