package com.teste.emprestimo.entity;

import com.teste.emprestimo.messages.Mensagem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @NotBlank(message = "Preencha o CPF com 11 digitos")
    @Size(max = 11, min = 11, message = "O CPF deve conter 11 digitos")
    private String cpf;
    private String nome;
    @NotBlank(message = "Preencha o telefone com 11 digitos")
    @Size(max = 11, min = 11, message = "O telefone deve conter 11 digitos")
    private String telefone;
    @Digits(integer = 10, fraction = 4, message = "Deve conter no maximo 10 digitos")
    private BigDecimal rendimentoMensal;
    @Embedded
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cpfCliente")
    private Set<Emprestimo> emprestimos;
    public Cliente() {
    }

    public Cliente(String cpf, String nome, String telefone, BigDecimal rendimentoMensal, Endereco endereco, Set<Emprestimo> emprestimos) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.rendimentoMensal = rendimentoMensal;
        this.endereco = endereco;
        this.emprestimos = emprestimos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(BigDecimal rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public Set<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(Set<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
