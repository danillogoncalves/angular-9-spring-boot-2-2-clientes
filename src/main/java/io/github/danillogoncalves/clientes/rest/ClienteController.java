package io.github.danillogoncalves.clientes.rest;

import io.github.danillogoncalves.clientes.model.entity.Cliente;
import io.github.danillogoncalves.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  private final ClienteRepository repository;

  @Autowired
  public ClienteController(final ClienteRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente salvar(@RequestBody Cliente cliente) {
    return repository.save(cliente);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cliente acharPorId(@PathVariable Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Integer id) {
    repository.findById(id).map(cliente -> {
      repository.delete(cliente);
      return Void.TYPE;
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizar(@RequestBody Cliente updateCliente) {
    Optional.ofNullable(updateCliente.getId())
            .map(repository::findById)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
            .ifPresent(cliente -> {
              cliente.setNome(Optional.ofNullable(updateCliente.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
              cliente.setNome(Optional.ofNullable(updateCliente.getCpf()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
              repository.save(updateCliente);
            });
  }

}
