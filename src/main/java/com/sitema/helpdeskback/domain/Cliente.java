package com.sitema.helpdeskback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sitema.helpdeskback.domain.dtos.ClienteDTO;
import com.sitema.helpdeskback.domain.dtos.TecnicoDTO;
import com.sitema.helpdeskback.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "TB_CLIENTE")
public class Cliente  extends  Pessoa{
    private static final long serialVersionUID = 1L;
    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
        private List<Chamado> chamados = new ArrayList<>();

    public Cliente(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        this.chamados = chamados;
    }
    public Cliente(ClienteDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }



    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
