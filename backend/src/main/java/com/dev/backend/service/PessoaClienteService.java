package com.dev.backend.service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PessoaClienteService {
    @Autowired
    private PessoaClienteRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;
    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
        pessoa.setDataCriacao(LocalDateTime.now());
        Pessoa objetoNovo = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(objetoNovo);
        Map<String, Object> proprMap = new HashMap<>();
        proprMap.put("nome", objetoNovo.getNome());
        proprMap.put("mensagem", "O registro na loja foi realizado com sucesso! Em breve você receberá a senha de acesso por e-mail!");
        emailService.enviarEmailTemplate(objetoNovo.getEmail(), "Cadastro na Loja 1618", proprMap);
        return objetoNovo;
    }



}
