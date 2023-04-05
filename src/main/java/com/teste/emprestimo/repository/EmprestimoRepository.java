package com.teste.emprestimo.repository;

import com.teste.emprestimo.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository <Emprestimo, Long> {
    List<Emprestimo>findByCpfCliente(String cpf);

}
