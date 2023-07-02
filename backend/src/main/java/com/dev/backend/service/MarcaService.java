package com.dev.backend.service;

import com.dev.backend.entity.Marca;
import com.dev.backend.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> buscarTodos() {
        return marcaRepository.findAll();
    }

    public Marca inserir(Marca objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Marca objetoNovo = marcaRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Marca alterar(Marca objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return marcaRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Marca objeto = marcaRepository.findById(id).get();
        marcaRepository.delete(objeto);
    }
}
