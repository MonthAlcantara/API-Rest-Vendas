package io.github.monthalcantara.mappers;

import io.github.monthalcantara.dto.request.ClientDTO;
import io.github.monthalcantara.dto.request.ClientDTO.ClientDTOBuilder;
import io.github.monthalcantara.dto.response.ClientResponseDTO;
import io.github.monthalcantara.dto.response.ClientResponseDTO.ClientResponseDTOBuilder;
import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.model.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T16:14:22-0300",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 11.0.8 (JetBrains s.r.o.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO clientToClientDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTOBuilder clientDTO = ClientDTO.builder();

        clientDTO.name( client.getName() );
        clientDTO.cpf( client.getCpf() );
        List<OrderItem> list = client.getOrderItems();
        if ( list != null ) {
            clientDTO.orderItems( new ArrayList<OrderItem>( list ) );
        }

        return clientDTO.build();
    }

    @Override
    public ClientResponseDTO clientToClientResponseDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponseDTOBuilder clientResponseDTO = ClientResponseDTO.builder();

        clientResponseDTO.id( client.getId() );
        clientResponseDTO.name( client.getName() );
        clientResponseDTO.cpf( client.getCpf() );
        List<OrderItem> list = client.getOrderItems();
        if ( list != null ) {
            clientResponseDTO.orderItems( new ArrayList<OrderItem>( list ) );
        }

        return clientResponseDTO.build();
    }

    @Override
    public Client clientDTOToClient(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setName( clientDTO.getName() );
        client.setCpf( clientDTO.getCpf() );
        List<OrderItem> list = clientDTO.getOrderItems();
        if ( list != null ) {
            client.setOrderItems( new ArrayList<OrderItem>( list ) );
        }

        return client;
    }

    @Override
    public Client clientResponseDTOToClient(ClientResponseDTO clientResponseDTO) {
        if ( clientResponseDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientResponseDTO.getId() );
        client.setName( clientResponseDTO.getName() );
        client.setCpf( clientResponseDTO.getCpf() );
        List<OrderItem> list = clientResponseDTO.getOrderItems();
        if ( list != null ) {
            client.setOrderItems( new ArrayList<OrderItem>( list ) );
        }

        return client;
    }
}
