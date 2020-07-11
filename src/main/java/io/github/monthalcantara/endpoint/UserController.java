package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.dto.request.CredentialsRequestDTO;
import io.github.monthalcantara.dto.response.TokenDTO;
import io.github.monthalcantara.exception.InvalidPasswordException;
import io.github.monthalcantara.model.UserLogin;
import io.github.monthalcantara.security.jwt.JwtService;
import io.github.monthalcantara.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserLogin userLogin) {
        return new ResponseEntity(userService.save(userLogin), HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody CredentialsRequestDTO userLogin) {
        try {
            UserLogin user = UserLogin.builder()
                    .login(userLogin.getLogin())
                    .password(userLogin.getPassword())
                    .build();
            UserDetails authenticate = userService.authenticate(user);
            String token = jwtService.createToken(user);
            return new TokenDTO(userLogin.getLogin(),token);

        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

        }
    }
}