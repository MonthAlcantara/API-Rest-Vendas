package io.github.monthalcantara.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.monthalcantara.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    @NotEmpty(message = "Name field is required")
    private String name;

    @NotEmpty(message = "CPF field is required")
    @CPF(message = "CPF invalid")
    private String cpf;

    @JsonIgnore
    private List<OrderItem> orderItems;

}
