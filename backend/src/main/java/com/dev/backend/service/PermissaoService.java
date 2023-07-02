package com.dev.backend.service;

import com.dev.backend.entity.Permissao;
import com.dev.backend.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PermissaoService {
    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodos() {
        return permissaoRepository.findAll();
    }

    public Permissao inserir(Permissao objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Permissao objetoNovo = permissaoRepository.saveAndFlush(objeto);
        return objeto;
    }

    public Permissao alterar(Permissao objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return permissaoRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Permissao objeto = permissaoRepository.findById(id).get();
        permissaoRepository.delete(objeto);
    }

}
