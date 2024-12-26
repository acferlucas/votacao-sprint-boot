package com.github.acferlucas.votacaoapispringboot.controller;

import com.github.acferlucas.votacaoapispringboot.model.Pauta;
import com.github.acferlucas.votacaoapispringboot.service.PautaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        Pauta novaPauta = pautaService.criarPauta(pauta);
        return new ResponseEntity<>(novaPauta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> listarPautas() {
        List<Pauta> pautas = pautaService.listarPautas();
        return ResponseEntity.ok(pautas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> buscarPautaPorId(@PathVariable Long id) {
        Pauta pauta = pautaService.buscarPautaPorId(id);
        return ResponseEntity.ok(pauta);
    }
}
