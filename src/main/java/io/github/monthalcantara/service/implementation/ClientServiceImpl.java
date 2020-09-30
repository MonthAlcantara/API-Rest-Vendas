package io.github.monthalcantara.service.implementation;

import io.github.monthalcantara.dto.request.ClientDTO;
import io.github.monthalcantara.dto.response.ClientResponseDTO;
import io.github.monthalcantara.exception.BusinessRuleException;
import io.github.monthalcantara.mappers.ClientMapper;
import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.repository.ClientRepository;
import io.github.monthalcantara.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapper clientMapper;



    @Override
    public ClientResponseDTO findByNameLike(String name) {
        Optional<Client> client = clientRepository.findByNameLike(name);
        return client.map(c -> clientMapper.clientToClientResponseDTO(c)).orElseThrow(() -> new BusinessRuleException("Client not found"));
    }

    @Override
    public ClientResponseDTO findById(Integer id) {
        Client client = clientExists(id);
        return clientMapper.clientToClientResponseDTO(client);
    }

    @Override
    public Page<ClientResponseDTO> findByName(String name, Pageable pageable) {
        Optional<Page<Client>> clients = clientRepository.findByName(name, pageable);
        return clients.map(c -> c.map(this::clientToClientResponseDTO)).orElseThrow(() -> new BusinessRuleException("Client not found"));
    }
    private ClientResponseDTO clientToClientResponseDTO(Client client){
        return clientMapper.clientToClientResponseDTO(client);
    }

    @Override
    public Page<ClientResponseDTO> findAll(Example example, Pageable pageable) {
        Optional<Page<Client>> clients = Optional.ofNullable(clientRepository.findAll(example, pageable));
        return clients.map(c -> c.map(this::clientToClientResponseDTO)).orElseThrow(() -> new BusinessRuleException("Client not found"));
    }

    @Override
    public Page<ClientResponseDTO> findAllByExample(Pageable pageable, ClientDTO clientDTO) {
      Client filter = clientMapper.clientDTOToClient(clientDTO);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return findAll(example, pageable);
    }

    @Override
    public ClientResponseDTO findClientFetchOrderItem(Integer id) {
        Optional<Client> client = clientRepository.findClientFetchOrderItem(id);
        return client.map(c -> clientMapper.clientToClientResponseDTO(c)).orElseThrow(() -> new BusinessRuleException("Client not found"));
    }

    @Override
    public boolean existsByName(String name) {
        return clientRepository.existsByName(name);
    }

    @Override
    public ClientResponseDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        return clientMapper.clientToClientResponseDTO(clientRepository.save(client));
    }

    @Override
    public void deleteByName(String name) {
        clientRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        clientExists(id);
        clientRepository.deleteById(id);
    }

    @Override
    public ClientResponseDTO updateById(Integer id, ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientOptional.get().setName(client.getName());
            clientOptional.get().setCpf(client.getCpf());
            clientOptional.get().setOrderItems(client.getOrderItems());
            clientRepository.save(clientOptional.get());
            return clientMapper.clientToClientResponseDTO(clientOptional.get());
        }
        return clientMapper.clientToClientResponseDTO(clientRepository.save(client));
    }

    public List<ClientResponseDTO> findAll(Example example) {

        return clientRepository.findAll(example);
    }

    private Client clientExists(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new BusinessRuleException("Client not found"));
    }
}
