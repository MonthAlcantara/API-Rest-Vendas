package io.github.monthalcantara.service.interfaces;

import io.github.monthalcantara.dto.request.ClientDTO;
import io.github.monthalcantara.dto.response.ClientResponseDTO;
import io.github.monthalcantara.model.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClientService {
    ClientResponseDTO findByNameLike(String name);

    ClientResponseDTO findById(Integer id);

    Page<ClientResponseDTO> findByName(String name, Pageable pageable);

    ClientResponseDTO findClientFetchOrderItem(Integer id);

    boolean existsByName(String name);

    ClientResponseDTO save(ClientDTO clientDTO);

    void deleteByName(String name);

    void deleteById(Integer id);

    ClientResponseDTO updateById(Integer id, ClientDTO clientDTO);

    Page<ClientResponseDTO> findAll(Example example, Pageable pageable);


    Page<ClientResponseDTO> findAllByExample(Pageable pageable, ClientDTO filter);
}
