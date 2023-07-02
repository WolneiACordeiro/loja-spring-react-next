package com.dev.backend.service;

import com.dev.backend.entity.ProdutoImagens;
import com.dev.backend.repository.ProdutoImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoImagensService {

    private ProdutoImagensRepository produtoImagensRepository;
    private String uploadDir;

    @Autowired
    public ProdutoImagensService(ProdutoImagensRepository produtoImagensRepository, @Value("${app.upload.dir}") String uploadDir) {
        this.produtoImagensRepository = produtoImagensRepository;
        this.uploadDir = uploadDir;
    }

    public List<ProdutoImagens> listarImagens() {
        return produtoImagensRepository.findAll();
    }

    public Optional<ProdutoImagens> buscarImagemPorId(Long id) {
        return produtoImagensRepository.findById(id);
    }

    public ProdutoImagens criarImagem(MultipartFile arquivo) throws IOException, NoSuchAlgorithmException {
        ProdutoImagens objeto = new ProdutoImagens();
        String nomeOriginal = arquivo.getOriginalFilename();
        objeto.setNome(nomeOriginal);

        String nomeCriptografado = gerarNomeCriptografado(nomeOriginal);
        String extensao = extrairExtensao(arquivo.getOriginalFilename());
        String nomeArquivo = nomeCriptografado + "." + extensao;

        Path uploadPath = Path.of(uploadDir);
        Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        objeto.setCaminhoImagem(filePath.toString());

        return produtoImagensRepository.saveAndFlush(objeto);
    }

    private String extrairExtensao(String nomeArquivo) {
        int indicePonto = nomeArquivo.lastIndexOf(".");
        if (indicePonto != -1) {
            return nomeArquivo.substring(indicePonto + 1);
        }
        return "";
    }

    private String gerarNomeCriptografado(String nomeOriginal) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(nomeOriginal.getBytes());
        String nomeCriptografado = bytesToHex(hashBytes);
        return nomeCriptografado;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public ProdutoImagens alterarImagem(Long id, MultipartFile arquivo) throws IOException {
        Optional<ProdutoImagens> imagemOptional = produtoImagensRepository.findById(id);
        if (imagemOptional.isPresent()) {
            ProdutoImagens objeto = imagemOptional.get();
            objeto.setNome(arquivo.getOriginalFilename());
            String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
            Path uploadPath = Path.of(uploadDir);
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(nomeArquivo);
            Files.copy(arquivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            objeto.setCaminhoImagem(filePath.toString());
            return produtoImagensRepository.save(objeto);
        } else {
            throw new IllegalArgumentException("Imagem não encontrada com o ID: " + id);
        }
    }

    public void excluirImagem(Long id) {
        ProdutoImagens objeto = produtoImagensRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Imagem não encontrada com o ID: " + id));
        Path imagePath = Path.of(objeto.getCaminhoImagem());
        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {

        }
        produtoImagensRepository.delete(objeto);
    }
}
