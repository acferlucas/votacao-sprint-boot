package com.github.acferlucas.votacaoapispringboot.service;

import com.github.acferlucas.votacaoapispringboot.exception.AssociadoJaVotouException;
import com.github.acferlucas.votacaoapispringboot.model.Sessao;
import com.github.acferlucas.votacaoapispringboot.model.Voto;
import com.github.acferlucas.votacaoapispringboot.repository.VotoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VotoService {
    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;

    public VotoService(VotoRepository votoRepository, SessaoService sessaoService) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
    }

    public Voto registrarVoto(Long pautaId, String associadoId, Voto.TipoVoto tipoVoto) {
        Sessao sessao = sessaoService.buscarSessaoAtivaPorPauta(pautaId);

        if (votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)) {
            throw new AssociadoJaVotouException("Associado com ID: " + associadoId + " já votou nesta pauta.");
        }

        Voto voto = new Voto();
        voto.setPauta(sessao.getPauta());
        voto.setAssociadoId(associadoId);
        voto.setVoto(tipoVoto);

        return votoRepository.save(voto);
    }

    public List<Voto> buscarVotosPorPauta(Long pautaId) {
        return votoRepository.findByPautaId(pautaId);
    }

    public String calcularResultado(Long pautaId) {
        List<Voto> votos = buscarVotosPorPauta(pautaId);

        long votosSim = votos.stream().filter(v -> v.getVoto() == Voto.TipoVoto.SIM).count();
        long votosNao = votos.stream().filter(v -> v.getVoto() == Voto.TipoVoto.NAO).count();

        return String.format("Resultado: SIM = %d, NÃO = %d", votosSim, votosNao);
    }
}
