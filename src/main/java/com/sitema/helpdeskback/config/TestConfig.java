package com.sitema.helpdeskback.config;

import com.sitema.helpdeskback.domain.Chamado;
import com.sitema.helpdeskback.domain.Cliente;
import com.sitema.helpdeskback.domain.Tecnico;
import com.sitema.helpdeskback.enums.Perfil;
import com.sitema.helpdeskback.enums.Prioridade;
import com.sitema.helpdeskback.enums.Status;
import com.sitema.helpdeskback.repositories.ChamadoRepository;
import com.sitema.helpdeskback.repositories.ClienteRepository;
import com.sitema.helpdeskback.repositories.TecnicoRepository;
import com.sitema.helpdeskback.service.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    ChamadoRepository chamadoRepository;
    @Override
    public void run(String... args) throws Exception {

        Tecnico tecnico01 = new Tecnico(null, "Valdir cezar", "02486624147", "valdir@mail.com", "123");
        tecnico01.addPerfil(Perfil.ADMIN);

        Cliente cliente01 = new Cliente(null, "Linu Torvalds", "02486624150","linu@mail.com", "123");

        Chamado chamado01 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro chamado", tecnico01, cliente01);

        tecnicoRepository.saveAll(Arrays.asList(tecnico01));
        clienteRepository.saveAll(Arrays.asList(cliente01));
        chamadoRepository.saveAll(Arrays.asList(chamado01));
    }
}
