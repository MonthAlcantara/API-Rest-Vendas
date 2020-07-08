package io.github.monthalcantara.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {

    private Integer code;

    private String cpf;

    private LocalDate date;

    private String clientName;

    private BigDecimal total;

    private String status;

    private List<ItemResponseDTO> items;

}
