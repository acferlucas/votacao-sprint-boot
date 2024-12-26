package com.github.acferlucas.votacaoapispringboot.repository;

import com.github.acferlucas.votacaoapispringboot.model.Pauta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
