package com.sitema.helpdeskback.repositories;

import com.sitema.helpdeskback.domain.Cliente;
import com.sitema.helpdeskback.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
