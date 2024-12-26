package com.github.acferlucas.votacaoapispringboot.service;

import com.github.acferlucas.votacaoapispringboot.exception.EntityNotFoundException;
import com.github.acferlucas.votacaoapispringboot.model.Pauta;
import com.github.acferlucas.votacaoapispringboot.repository.PautaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada com ID: " + id));
    }
}
