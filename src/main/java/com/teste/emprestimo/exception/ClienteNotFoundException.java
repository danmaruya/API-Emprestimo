package com.teste.emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ClienteNotFoundException(String cpf) {
        super(String.format("Cliente com o CPF %s nao encontrado", cpf));
    }
}
