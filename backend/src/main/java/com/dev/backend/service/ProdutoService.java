package com.dev.backend.service;

import com.dev.backend.entity.Produto;
import com.dev.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto inserir(Produto objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Produto objetoNovo = produtoRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Produto alterar(Produto objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return produtoRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Produto objeto = produtoRepository.findById(id).get();
        produtoRepository.delete(objeto);
    }
}
