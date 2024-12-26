package com.github.acferlucas.votacaoapispringboot.repository;

import com.github.acferlucas.votacaoapispringboot.model.Sessao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Optional<Sessao> findByPautaId(Long pautaId);
    Optional<Sessao> findByPautaIdAndFimAfter(Long pautaId, LocalDateTime now);
    boolean existsByPautaIdAndFimAfter(Long pautaId, LocalDateTime dataHoraAtual);
}
