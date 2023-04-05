package com.teste.emprestimo.repository;

import com.teste.emprestimo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, String> {
}
