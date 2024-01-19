package com.sitema.helpdeskback.repositories;

import com.sitema.helpdeskback.domain.Cliente;
import com.sitema.helpdeskback.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);
}
