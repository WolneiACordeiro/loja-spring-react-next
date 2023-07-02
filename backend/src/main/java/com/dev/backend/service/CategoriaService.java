package com.dev.backend.service;

import com.dev.backend.entity.Categoria;
import com.dev.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria inserir(Categoria objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Categoria objetoNovo = categoriaRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Categoria alterar(Categoria objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return categoriaRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Categoria objeto = categoriaRepository.findById(id).get();
        categoriaRepository.delete(objeto);
    }
}
