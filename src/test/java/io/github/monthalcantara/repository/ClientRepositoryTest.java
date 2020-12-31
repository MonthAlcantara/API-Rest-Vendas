package io.github.monthalcantara.repository;


import io.github.monthalcantara.model.Client;
import io.github.monthalcantara.util.ClientGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientRepositoryTest {

    private Pageable pageable = PageRequest.of(1, 1);


    @Autowired
    private ClientRepository repository;

    @BeforeEach
    void init(){
        Client save = repository.save(ClientGenerator.criaClientASerSalvo());
        System.out.println(save.getId());
    }

    @Test
    @DisplayName("Deve buscar cliente pelo nome")
    @Order(1)
    void findByName() {

        Page<Client> optionalPage = repository.findByName("Teste", pageable);
        Assertions.assertTrue(optionalPage.getSize() > 0);
        Assertions.assertNotNull(optionalPage.getContent());
    }

    @Order(2)
    @Test
    @DisplayName("Deve retornar true se existe registro com nome")
    void existsByName() {

        boolean existsByName = repository.existsByName("Teste");
        Assertions.assertTrue(existsByName);
    }

    @Order(3)
    @ParameterizedTest()
    @CsvSource({"es,true","Te,true","est, true", "ab,false"})
    @DisplayName("Deve buscar cliente com nome iquais a")
    void findByNameLike(String nome, boolean resultadoEsperado) {
        Optional<Client> resultado = repository.findByNameLike(nome);
        Assertions.assertEquals(resultado.isPresent(),resultadoEsperado);
    }

    @Test
    @DisplayName("Deve deletar por nome")
    @Order(4)
    void deleteByName() {
        repository.deleteByName("Teste");
        List<Client> all = repository.findAll();
        Assertions.assertTrue(all.isEmpty());
    }



    @Test
    @Order(5)
    @DisplayName("Deve Buscar todos os registros do banco")
    void findAll() {

        List<Client> all = repository.findAll();
        System.out.println(all.size());
        Assertions.assertFalse(all.isEmpty());
    }
}