package com.sitema.helpdeskback.repositories;

import com.sitema.helpdeskback.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
