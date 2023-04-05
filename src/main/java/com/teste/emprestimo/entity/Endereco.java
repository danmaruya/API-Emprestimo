package com.teste.emprestimo.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Embeddable
public class Endereco {
    @NotBlank(message = "O endereco nao deve ficar em branco")
    @Size(max = 50, min = 5)
    private String rua;
    @NotBlank(message = "O numero nao deve ficar em branco")
    @Size(min = 1, message = "Deve conter no maximo 9 digitos")
    private String numero;
    @NotBlank(message = "O CEP nao deve ficar em branco")
    @Size(min = 8, max = 9, message = "Deve conter no maximo 9 digitos")
    private String cep;

    public Endereco() {
    }

    public Endereco(String rua, String numero, String cep) {
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
