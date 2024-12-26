package com.github.acferlucas.votacaoapispringboot.repository;

import com.github.acferlucas.votacaoapispringboot.model.Voto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    long countByPautaIdAndVoto(Long pautaId, Voto.TipoVoto voto);
    List<Voto> findByPautaId(Long pautaId);
    boolean existsByPautaIdAndAssociadoId(Long pautaId, String associadoId);
}
