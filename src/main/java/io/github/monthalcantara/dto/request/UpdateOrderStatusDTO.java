package io.github.monthalcantara.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateOrderStatusDTO implements Serializable {

    private String newStatus;
}
