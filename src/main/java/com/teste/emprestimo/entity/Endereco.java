package com.teste.emprestimo.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Embeddable
public class Endereco {
    @NotBlank(message = "Preencha com um endereco valido")
    @Size(max = 50, min = 5)
    private String rua;
    @Digits(integer = 4, fraction = 4, message = "Deve conter no maximo 4 digitos")
    private int numero;
    @NotBlank(message = "Preencha com um CEP valido")
    @Size(max = 9, message = "Deve conter no maximo 9 digitos")
    private String cep;

    public Endereco() {
    }

    public Endereco(String rua, int numero, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
