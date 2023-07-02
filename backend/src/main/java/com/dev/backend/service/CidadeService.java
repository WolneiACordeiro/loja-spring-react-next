package com.dev.backend.service;

import com.dev.backend.entity.Cidade;
import com.dev.backend.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> buscarTodos(){
        return cidadeRepository.findAll();
    }

    public Cidade inserir(Cidade objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Cidade objetoNovo = cidadeRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Cidade alterar(Cidade objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return cidadeRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Cidade objeto = cidadeRepository.findById(id).get();
        cidadeRepository.delete(objeto);
    }

}
