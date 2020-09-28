package io.github.monthalcantara.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRequestDTO implements Serializable {

    private String login;

    private String password;
}
