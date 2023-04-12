package com.teste.emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

    public ClienteAlreadyExists(String cpf) {
        super(String.format("Cliente com o CPF %s ja existe", cpf));
    }
}
