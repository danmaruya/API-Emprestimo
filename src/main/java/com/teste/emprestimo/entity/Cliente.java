package com.teste.emprestimo.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @NotBlank(message = "Preencha o CPF com 11 digitos, sem pontos e hifen")
    @Size(max = 11, min = 11, message = "O CPF deve conter 11 digitos")
    private String cpf;
    private String nome;
    @NotBlank(message = "Preencha o telefone com 11 digitos")
    @Size(max = 11, min = 10, message = "O telefone deve conter no maximo 11 digitos. Colocar somente numeros")
    private String telefone;
    @Digits(integer = 10, fraction = 4, message = "Deve conter no maximo 10 digitos")
    @NotNull(message = "O campo Rendimento Mensal nao pode ser em branco")
    private BigDecimal rendimentoMensal;
    @Valid
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

    public void setCpf(String cpf) throws IllegalArgumentException {
        if (this.cpf == null) {
            this.cpf = cpf;
        } else if (!this.cpf.equals(cpf)) {
            throw new IllegalArgumentException("O CPF não pode ser alterado");
        }
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
