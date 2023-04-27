package com.teste.emprestimo.service;

import com.teste.emprestimo.entity.Cliente;
import com.teste.emprestimo.entity.Emprestimo;
import com.teste.emprestimo.exception.BadRequestException;
import com.teste.emprestimo.exception.EmprestimoNotFoundException;
import com.teste.emprestimo.messages.Mensagem;
import com.teste.emprestimo.repository.ClienteRepository;
import com.teste.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmprestimoService {
    private EmprestimoRepository emprestimoRepository;

    private ClienteRepository clienteRepository;

    private static int quantidadeEmprestimos = 0;

    private BigDecimal rendimentoMensal;

    public static int getQuantidadeEmprestimos() {
        return quantidadeEmprestimos;
    }

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) { this.emprestimoRepository = emprestimoRepository; }

    @Autowired
    public void ClienteService(ClienteRepository clienteRepository) { this.clienteRepository = clienteRepository; }

    public Mensagem cadastrarEmprestimo (Emprestimo emprestimo) throws BadRequestException {
        Mensagem resultadoCadastroEmprestimo = new Mensagem();

        Optional<Cliente> cliente = this.clienteRepository.findById(emprestimo.getCpfCliente());

        if (cliente.isPresent()) {
            this.rendimentoMensal = cliente.get().getRendimentoMensal();
            boolean elegivel = verificarElegibilidadeClienteEmprestimo(emprestimo, rendimentoMensal);
            if (elegivel) {
                emprestimo.setValorFinal();
                this.emprestimoRepository.save(emprestimo);
                resultadoCadastroEmprestimo.setMensagem("Emprestimo cadastrado");
            } else {
                resultadoCadastroEmprestimo.setMensagem("Voce nao possui margem para emprestimo");
            }
        } else {
            resultadoCadastroEmprestimo.setMensagem("Cliente nao cadastrado. Por favor, faca o cadastro do cliente");
        }
        return resultadoCadastroEmprestimo;
    }

    private boolean verificarElegibilidadeClienteEmprestimo(Emprestimo emprestimo, BigDecimal rendimentoMensal) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByCpfCliente(emprestimo.getCpfCliente());
        BigDecimal valorTotalEmprestimoAnteriores = BigDecimal.ZERO;
        for (Emprestimo item : emprestimos) {
            valorTotalEmprestimoAnteriores = valorTotalEmprestimoAnteriores.add(item.getValorInicial());
        }
        valorTotalEmprestimoAnteriores = valorTotalEmprestimoAnteriores.add(emprestimo.getValorInicial());
        this.rendimentoMensal = rendimentoMensal.multiply(new BigDecimal(10));
        if (this.rendimentoMensal.compareTo(valorTotalEmprestimoAnteriores) >= 0) {
            quantidadeEmprestimos = emprestimos.size();
            emprestimo.setValorFinal();
            this.emprestimoRepository.save(emprestimo);
            return true;
        } else {
            return false;
        }
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

    public List<Emprestimo> retornarTodosOsEmprestimosDoCliente(Emprestimo emprestimo) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByCpfCliente(emprestimo.getCpfCliente());
        return emprestimos;
    }

}
