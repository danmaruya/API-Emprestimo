package com.teste.emprestimo.service;

import com.teste.emprestimo.entity.Cliente;
import com.teste.emprestimo.entity.Emprestimo;
import com.teste.emprestimo.exception.EmprestimoNotFoundException;
import com.teste.emprestimo.messages.Mensagem;
import com.teste.emprestimo.repository.ClienteRepository;
import com.teste.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private EmprestimoRepository emprestimoRepository;

    private ClienteRepository clienteRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) { this.emprestimoRepository = emprestimoRepository; }

    @Autowired
    public void ClienteService(ClienteRepository clienteRepository) { this.clienteRepository = clienteRepository; }

    public Mensagem cadastrarEmprestimo (Emprestimo emprestimo) {
        Mensagem mensagem = new Mensagem();

        Optional<Cliente> cliente = this.clienteRepository.findById(emprestimo.getCpfCliente());
        List<Emprestimo> emprestimos = emprestimoRepository.findByCpfCliente(emprestimo.getCpfCliente());
        BigDecimal somaValorInicial = new BigDecimal(0);
        if (!cliente.isEmpty()) {
            for (Emprestimo item : emprestimos) {
                somaValorInicial = somaValorInicial.add(item.getValorInicial());
            }
            somaValorInicial = somaValorInicial.add(emprestimo.getValorInicial());
            BigDecimal rendimentoMensal = cliente.get().getRendimentoMensal().multiply(new BigDecimal(10));
            if (rendimentoMensal.compareTo(somaValorInicial) >= 0) {
                this.emprestimoRepository.save(emprestimo);
                mensagem.setMensagem("Emprestimo cadastrado");
                return mensagem;
            } else {
                mensagem.setMensagem("Voce nao possui margem para emprestimo");
                return mensagem;
            }
        }
        mensagem.setMensagem("Cliente nao cadastrado");
        return mensagem;
    }

    public Mensagem deletarEmprestimo (Long id) throws EmprestimoNotFoundException {
        if (this.emprestimoRepository.existsById(id)) {
            this.emprestimoRepository.deleteById(id);
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;
        }
            throw new EmprestimoNotFoundException(id);
    }

    public Emprestimo retornarEmprestimo (Long id) throws EmprestimoNotFoundException {
        if (this.emprestimoRepository.existsById(id)) {
            return this.emprestimoRepository.findById(id).get();
        }
        throw new EmprestimoNotFoundException(id);
    }

    public List<Emprestimo> retornarTodosOsEmprestimos() {
        return this.emprestimoRepository.findAll();
    }
}
