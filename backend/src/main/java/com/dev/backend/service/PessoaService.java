package com.dev.backend.service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PermissaoRepository;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodos(){
        return pessoaRepository.findAll();
    }

    public Pessoa inserir(Pessoa objeto){
        objeto.setDataCriacao(LocalDateTime.now());
        Pessoa objetoNovo = pessoaRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Pessoa alterar(Pessoa objeto){
        objeto.setDataAtualizacao(LocalDateTime.now());
        return pessoaRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id){
        Pessoa objeto = pessoaRepository.findById(id).get();
        pessoaRepository.delete(objeto);
    }

}
