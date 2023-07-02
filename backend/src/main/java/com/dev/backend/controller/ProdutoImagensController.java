package com.dev.backend.controller;

import com.dev.backend.entity.ProdutoImagens;
import com.dev.backend.service.ProdutoImagensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/imagens")
public class ProdutoImagensController {
    @Autowired
    private ProdutoImagensService produtoImagensService;

    public ProdutoImagensController(ProdutoImagensService produtoImagensService) {
        this.produtoImagensService = produtoImagensService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoImagens>> listarImagens() {
        List<ProdutoImagens> imagens = produtoImagensService.listarImagens();
        return ResponseEntity.ok(imagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoImagens> buscarImagemPorId(@PathVariable Long id) {
        Optional<ProdutoImagens> imagemOptional = produtoImagensService.buscarImagemPorId(id);
        if (imagemOptional.isPresent()) {
            ProdutoImagens produtoImagens = imagemOptional.get();
            return ResponseEntity.ok(produtoImagens);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProdutoImagens> criarImagem(@RequestParam("arquivo") MultipartFile arquivo) throws IOException, NoSuchAlgorithmException {
        ProdutoImagens novaProdutoImagens = produtoImagensService.criarImagem(arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaProdutoImagens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoImagens> alterarImagem(@PathVariable Long id, @RequestParam("arquivo") MultipartFile arquivo) throws IOException, SQLException {
        ProdutoImagens produtoImagensAlterada = produtoImagensService.alterarImagem(id, arquivo);
        return ResponseEntity.ok(produtoImagensAlterada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirImagem(@PathVariable Long id) {
        Optional<ProdutoImagens> imagemOptional = produtoImagensService.buscarImagemPorId(id);
        if (imagemOptional.isPresent()) {
            produtoImagensService.excluirImagem(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

