package com.teste.emprestimo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpfCliente;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
//    private enum Relacionamento
    private String dataInicial;
    private String dataFinal;

    public Emprestimo() {
    }

    public Emprestimo(long id, String cpfCliente, BigDecimal valorInicial, BigDecimal valorFinal, String dataInicial, String dataFinal) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
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

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
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

}
