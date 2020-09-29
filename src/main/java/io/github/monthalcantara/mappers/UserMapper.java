package io.github.monthalcantara.mappers;

import io.github.monthalcantara.dto.request.UserLoginDTO;
import io.github.monthalcantara.dto.response.UserLoginResponseDTO;
import io.github.monthalcantara.model.UserLogin;
import org.mapstruct.Mapper;



@Mapper(componentModel="spring")
public interface UserMapper {


    UserLogin userLoginDTOToUserLogin(UserLoginDTO userLoginDTO);

    UserLoginResponseDTO userLoginToUserLoginResponseDTO(UserLogin userLogin);


}
