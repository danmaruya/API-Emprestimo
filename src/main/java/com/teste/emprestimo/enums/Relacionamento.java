package com.teste.emprestimo.enums;

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
            if (valorEmprestimo.compareTo(new BigDecimal(5000)) <= 0) { //criar uma constante para substituir o 5k
                BigDecimal fatorMultiplicador = new BigDecimal(1.6);
                return valorEmprestimo.multiply(fatorMultiplicador, MathContext.DECIMAL32);
            } else {
                BigDecimal fatorMultiplicador = new BigDecimal(1.4);
                return valorEmprestimo.multiply(fatorMultiplicador, MathContext.DECIMAL32);
            }
        }
    },

    Ouro(3) {
        @Override
      public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorEmprestimo) {
            if (EmprestimoService.getQuantidadeEmprestimos() == 0) {
                BigDecimal fatorMultiplicador = new BigDecimal(1.2);
                return valorEmprestimo.multiply(fatorMultiplicador, MathContext.DECIMAL32);
            } else {
                BigDecimal fatorMultiplicador = new BigDecimal(1.3);
                return valorEmprestimo.multiply(fatorMultiplicador, MathContext.DECIMAL32);
            }
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
}
