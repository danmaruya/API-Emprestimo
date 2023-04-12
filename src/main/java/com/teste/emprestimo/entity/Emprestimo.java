package com.teste.emprestimo.entity;

import com.teste.emprestimo.enums.Relacionamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpfCliente;
    @Digits(integer = 10, fraction = 4, message = "Deve conter no maximo 10 digitos")
    @NotNull(message = "Este campo nao pode ser em branco")
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private Relacionamento relacionamento;
    private String dataInicial;
    private String dataFinal;

    public Emprestimo() {
    }

    public Emprestimo(long id, String cpfCliente, BigDecimal valorInicial, BigDecimal valorFinal, String dataInicial, String dataFinal, Relacionamento relacionamento) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.relacionamento = relacionamento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal() {
        this.valorFinal = this.relacionamento.calculaValorFinalEmprestimo(valorInicial);
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }
}
