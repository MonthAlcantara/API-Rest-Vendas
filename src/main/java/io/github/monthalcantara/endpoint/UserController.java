package io.github.monthalcantara.endpoint;

import io.github.monthalcantara.model.UserLogin;
import io.github.monthalcantara.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserLogin userLogin){
        return new ResponseEntity(userService.save(userLogin), HttpStatus.CREATED);
    }
}
