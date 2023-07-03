package com.dev.backend.controller;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.service.PessoaGerenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa-gerenciamento")
public class PessoaGerenciamentoController {
    @Autowired
    private PessoaGerenciamentoService pessoaGerenciamentoService;

   @PostMapping("/senha-codigo")
    public String recuperarCodigo(@RequestParam("email") String email){
       return pessoaGerenciamentoService.solicitarCodigo(email);
   }

    @PostMapping("/senha-alterar")
    public String alterarSenha(@RequestBody Pessoa pessoa){
        return pessoaGerenciamentoService.alterarSenha(pessoa);
    }

}
