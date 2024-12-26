package com.github.acferlucas.votacaoapispringboot.controller;

import com.github.acferlucas.votacaoapispringboot.model.Voto;
import com.github.acferlucas.votacaoapispringboot.service.VotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/votos")
public class VotoController {
    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/{pautaId}")
    public ResponseEntity<Voto> registrarVoto(
            @PathVariable Long pautaId,
            @RequestParam String associadoId,
            @RequestParam Voto.TipoVoto tipoVoto
    ) {

        Voto voto = votoService.registrarVoto(pautaId, associadoId, tipoVoto);
        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }

    @GetMapping("/{pautaId}/resultado")
    public ResponseEntity<String> calcularResultado(@PathVariable Long pautaId) {
        String resultado = votoService.calcularResultado(pautaId);
        return ResponseEntity.ok(resultado);
    }
}
