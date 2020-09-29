package io.github.monthalcantara.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO implements Serializable {

    @NotEmpty(message = "field login cannot be empty")
    private String login;

    @NotEmpty(message = "field password cannot be empty")
    private String password;

}
