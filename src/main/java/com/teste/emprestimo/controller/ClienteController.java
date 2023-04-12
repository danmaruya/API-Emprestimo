package com.teste.emprestimo.controller;

import com.teste.emprestimo.entity.Cliente;
import com.teste.emprestimo.exception.BadRequestException;
import com.teste.emprestimo.exception.ClienteAlreadyExists;
import com.teste.emprestimo.exception.ClienteNotFoundException;
import com.teste.emprestimo.messages.Mensagem;
import com.teste.emprestimo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) { this.clienteService = clienteService; }

    @PostMapping
    public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) throws ClienteAlreadyExists {
        return this.clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> retornarTodosOsClientes() {
        return this.clienteService.retornarTodosOsClientes();
    }

    @GetMapping("/{cpf}")
    public Cliente retornarClientePorCpf(@PathVariable String cpf) {
        try {
            return this.clienteService.retornarClientePorCpf(cpf);
        }
        catch (ClienteNotFoundException e) {
            System.out.println(e);
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{cpf}")
    public Mensagem deletarCliente(@PathVariable String cpf) throws ClienteNotFoundException {
        return this.clienteService.deletarCliente(cpf);
    }

    @PutMapping("/{cpf}")
    public Cliente alterarCliente(@PathVariable String cpf, @Valid @RequestBody Cliente cliente) throws BadRequestException, ClienteNotFoundException {
        return this.clienteService.alterarCliente(cliente, cpf);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        throw new BadRequestException(errors);
    }
}
