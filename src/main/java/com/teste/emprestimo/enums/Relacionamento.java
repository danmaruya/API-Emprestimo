package com.teste.emprestimo.enums;

import com.teste.emprestimo.entity.Emprestimo;
import com.teste.emprestimo.service.EmprestimoService;

import java.math.BigDecimal;
import java.math.MathContext;


public enum Relacionamento {

    Bronze(1) {
        @Override
        public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorEmprestimo) {
            BigDecimal fatorMultiplicador = new BigDecimal(1.8);
            return valorEmprestimo.multiply(fatorMultiplicador, MathContext.DECIMAL32);
        }
    },

    Prata(2) {
        @Override
        public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorEmprestimo) {
            return emprestimo.relacionamentoPrata(valorEmprestimo);
        }
    },

    Ouro(3) {
        @Override
      public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorEmprestimo) {
            return emprestimo.relacionamentoOuro(valorEmprestimo);
      }
    };

    private int codigo;
    private Relacionamento(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public abstract BigDecimal calculaValorFinalEmprestimo(BigDecimal valorEmprestimo);

    Emprestimo emprestimo = new Emprestimo();

}
