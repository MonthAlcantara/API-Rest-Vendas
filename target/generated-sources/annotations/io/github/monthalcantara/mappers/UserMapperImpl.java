package io.github.monthalcantara.mappers;

import io.github.monthalcantara.dto.request.UserLoginDTO;
import io.github.monthalcantara.dto.response.UserLoginResponseDTO;
import io.github.monthalcantara.dto.response.UserLoginResponseDTO.UserLoginResponseDTOBuilder;
import io.github.monthalcantara.model.UserLogin;
import io.github.monthalcantara.model.UserLogin.UserLoginBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T16:14:23-0300",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 11.0.8 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserLogin userLoginDTOToUserLogin(UserLoginDTO userLoginDTO) {
        if ( userLoginDTO == null ) {
            return null;
        }

        UserLoginBuilder userLogin = UserLogin.builder();

        userLogin.login( userLoginDTO.getLogin() );
        userLogin.password( userLoginDTO.getPassword() );

        return userLogin.build();
    }

    @Override
    public UserLoginResponseDTO userLoginToUserLoginResponseDTO(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }

        UserLoginResponseDTOBuilder userLoginResponseDTO = UserLoginResponseDTO.builder();

        userLoginResponseDTO.id( userLogin.getId() );
        userLoginResponseDTO.login( userLogin.getLogin() );
        userLoginResponseDTO.admin( userLogin.isAdmin() );

        return userLoginResponseDTO.build();
    }
}
