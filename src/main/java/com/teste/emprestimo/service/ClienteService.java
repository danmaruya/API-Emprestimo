package com.teste.emprestimo.service;

import com.teste.emprestimo.entity.Cliente;
import com.teste.emprestimo.exception.BadRequestException;
import com.teste.emprestimo.exception.ClienteAlreadyExists;
import com.teste.emprestimo.exception.ClienteNotFoundException;
import com.teste.emprestimo.messages.Mensagem;
import com.teste.emprestimo.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) { this.clienteRepository = clienteRepository; }

    public Cliente cadastrarCliente(Cliente cliente) throws ClienteAlreadyExists {
        String cpf = cliente.getCpf();
        if (this.clienteRepository.existsById(cpf)) {
            throw new ClienteAlreadyExists(cpf);
        }
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> retornarTodosOsClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente retornarClientePorCpf(String cpf) {
        if (this.clienteRepository.existsById(cpf)) {
            return this.clienteRepository.findById(cpf).get();
        }
        throw new ClienteNotFoundException(cpf);
    }

    public Mensagem deletarCliente(String cpf) throws ClienteNotFoundException {
        if (this.clienteRepository.existsById(cpf)) {
            this.clienteRepository.deleteById(cpf);
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("Deletado com sucesso.");
            return mensagem;
        }
        throw new ClienteNotFoundException(cpf);
    }

    public Cliente alterarCliente(@Valid Cliente cliente, String cpf) throws BadRequestException, ClienteNotFoundException {

        if (this.clienteRepository.existsById(cpf)) {
            Cliente clienteAlterado = this.clienteRepository.getReferenceById(cpf);
            clienteAlterado.setCpf(cliente.getCpf());
            clienteAlterado.setNome(cliente.getNome());
            clienteAlterado.setEndereco(cliente.getEndereco());
            clienteAlterado.setTelefone(cliente.getTelefone());
            clienteAlterado.setRendimentoMensal(cliente.getRendimentoMensal());

            return this.clienteRepository.save(clienteAlterado);
        }
        throw new ClienteNotFoundException(cpf);
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
