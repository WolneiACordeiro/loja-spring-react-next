package com.dev.backend.service;

import com.dev.backend.entity.Estado;
import com.dev.backend.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstadoService {

    private EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> buscarTodos(){
        return estadoRepository.findAll();
    }

    public Estado inserir(Estado objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Estado estadoNovo = estadoRepository.saveAndFlush(objeto);
        return estadoNovo;
    }

    public Estado alterar(Estado objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return estadoRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Estado objeto = estadoRepository.findById(id).get();
        estadoRepository.delete(objeto);
    }
}
