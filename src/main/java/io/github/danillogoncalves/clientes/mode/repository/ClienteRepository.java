package io.github.danillogoncalves.clientes.mode.repository;

import io.github.danillogoncalves.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
