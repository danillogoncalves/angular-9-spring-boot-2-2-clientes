package io.github.danillogoncalves.clientes.model.repository;

import io.github.danillogoncalves.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
