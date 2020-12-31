package io.github.monthalcantara.util;

import io.github.monthalcantara.model.Client;

public class ClientGenerator {

    public static Client criaClientASerSalvo(){
        return Client
                .builder()
                .name("Teste")
                .cpf("95962251032")
                .orderItems(null)
                .build();
    }
    public static Client criaClientSalvo(){
        return Client
                .builder()
                .id(1)
                .name("Teste")
                .cpf("95962251032")
                .build();
    }
}
