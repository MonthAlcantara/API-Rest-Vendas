package io.github.monthalcantara.mappers;


import io.github.monthalcantara.dto.request.ClientDTO;
import io.github.monthalcantara.dto.response.ClientResponseDTO;
import io.github.monthalcantara.model.Client;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client client);

    ClientResponseDTO clientToClientResponseDTO(Client client);

    Client clientDTOToClient(ClientDTO clientDTO);

    Client clientResponseDTOToClient(ClientResponseDTO clientResponseDTO);

    //Page<ClientResponseDTO> toPageResponseDTO(Page<Client> clientPage);

}
