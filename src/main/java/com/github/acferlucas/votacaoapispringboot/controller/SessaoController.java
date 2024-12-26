package com.github.acferlucas.votacaoapispringboot.controller;

import com.github.acferlucas.votacaoapispringboot.dto.ResultadoVotacao;
import com.github.acferlucas.votacaoapispringboot.model.Sessao;
import com.github.acferlucas.votacaoapispringboot.service.SessaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {
    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("/abrir/{pautaId}")
    public ResponseEntity<Sessao> abrirSessao(@PathVariable Long pautaId, @RequestParam(required = false) int duracaoEmMinutos) {
        Sessao sessao = sessaoService.abrirSessao(pautaId, duracaoEmMinutos);
        return new ResponseEntity<>(sessao, HttpStatus.CREATED);
    }

    @GetMapping("/{pautaId}/resultado")
    public ResponseEntity<ResultadoVotacao> obterResultado(@PathVariable Long pautaId) {
        ResultadoVotacao resultado = sessaoService.calcularResultado(pautaId);
        return ResponseEntity.ok(resultado);
    }
}
