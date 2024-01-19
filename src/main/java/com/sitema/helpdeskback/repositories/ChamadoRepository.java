package com.sitema.helpdeskback.repositories;

import com.sitema.helpdeskback.domain.Chamado;
import com.sitema.helpdeskback.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
