package com.teste.emprestimo.service;

import com.teste.emprestimo.entity.Cliente;
import com.teste.emprestimo.exception.ClienteNotFoundException;
import com.teste.emprestimo.messages.Mensagem;
import com.teste.emprestimo.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) { this.clienteRepository = clienteRepository; }

    public Cliente cadastrarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public List<Cliente> retornarTodosOsClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente retornarClientePorCpf(String cpf) throws ClienteNotFoundException {
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

    public Cliente alterarCliente(@Valid Cliente cliente, String cpf) throws ClienteNotFoundException {
        if (this.clienteRepository.existsById(cpf)) {
            Cliente clienteAlterado = this.clienteRepository.getReferenceById(cpf);
            clienteAlterado.setNome(cliente.getNome());
            clienteAlterado.setEndereco(cliente.getEndereco());
            clienteAlterado.setTelefone(cliente.getTelefone());
            clienteAlterado.setRendimentoMensal(cliente.getRendimentoMensal());

            return this.clienteRepository.save(clienteAlterado);
        }
        throw new ClienteNotFoundException(cpf);
    }

    public boolean checarCliente(String cpf) {
        if (this.clienteRepository.existsById(cpf)) {
            return true;
        }
        return false;
    }
}
