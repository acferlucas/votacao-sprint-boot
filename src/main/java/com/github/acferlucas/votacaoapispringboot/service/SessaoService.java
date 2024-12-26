package com.github.acferlucas.votacaoapispringboot.service;

import com.github.acferlucas.votacaoapispringboot.dto.ResultadoVotacao;
import com.github.acferlucas.votacaoapispringboot.exception.EntityNotFoundException;
import com.github.acferlucas.votacaoapispringboot.model.Pauta;
import com.github.acferlucas.votacaoapispringboot.model.Sessao;

import com.github.acferlucas.votacaoapispringboot.model.Voto;
import com.github.acferlucas.votacaoapispringboot.repository.SessaoRepository;

import com.github.acferlucas.votacaoapispringboot.repository.VotoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SessaoService {
    private final SessaoRepository sessaoRepository;
    private final VotoRepository votoRepository;
    private final PautaService pautaService;

    public SessaoService(SessaoRepository sessaoRepository,VotoRepository votoRepository, PautaService pautaService) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.pautaService = pautaService;
    }
    public Sessao abrirSessao(Long pautaId, Integer duracaoEmMinutos) {
        Pauta pauta = pautaService.buscarPautaPorId(pautaId);

        if (sessaoRepository.existsByPautaIdAndFimAfter(pautaId, LocalDateTime.now())) {
            throw new IllegalStateException("Já existe uma sessão ativa para esta pauta.");
        }

        if (duracaoEmMinutos == null || duracaoEmMinutos <= 0) {
            duracaoEmMinutos = 1;
        }

        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setInicio(LocalDateTime.now());
        sessao.setFim(LocalDateTime.now().plusMinutes(duracaoEmMinutos));

        return sessaoRepository.save(sessao);
    }
    public Sessao buscarSessaoAtivaPorPauta(Long pautaId) {
        return sessaoRepository.findByPautaIdAndFimAfter(pautaId, LocalDateTime.now())
                .orElseThrow(() -> new RuntimeException("Sessão ativa não encontrada para esta pauta"));
    }

    public ResultadoVotacao calcularResultado(Long pautaId) {
        Sessao sessao = sessaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada para a pauta com ID: " + pautaId));

        if (LocalDateTime.now().isBefore(sessao.getFim())) {
            throw new IllegalStateException("A sessão ainda está em andamento. Aguarde o encerramento.");
        }

        long votosSim = votoRepository.countByPautaIdAndVoto(pautaId, Voto.TipoVoto.SIM);
        long votosNao = votoRepository.countByPautaIdAndVoto(pautaId, Voto.TipoVoto.NAO);

        return new ResultadoVotacao(votosSim, votosNao);
    }

}
