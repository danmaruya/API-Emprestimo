package com.teste.emprestimo.controller;

import com.teste.emprestimo.entity.Emprestimo;
import com.teste.emprestimo.exception.EmprestimoNotFoundException;
import com.teste.emprestimo.messages.Mensagem;
import com.teste.emprestimo.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes/{cpfCliente}/emprestimos")
public class EmprestimoController {
    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) { this.emprestimoService = emprestimoService; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mensagem> cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo) {
        Mensagem mensagem = this.emprestimoService.cadastrarEmprestimo(emprestimo);
        if (mensagem.getMensagem().equals("Emprestimo cadastrado")) {
            return ResponseEntity.ok(mensagem);
        } else {
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mensagem deletarEmprestimo(@PathVariable String cpfCliente, @PathVariable Long id) throws EmprestimoNotFoundException {
        return this.emprestimoService.deletarEmprestimo(id);
    }

    @GetMapping("/{id}")
    public Emprestimo retornarEmprestimo(@PathVariable String cpfCliente, @PathVariable Long id) throws EmprestimoNotFoundException {
        return this.emprestimoService.retornarEmprestimo(id);
    }

    @GetMapping
    public List<Emprestimo> retornarTodosOsEmprestimos() {
        return this.emprestimoService.retornarTodosOsEmprestimos();
    }
}
