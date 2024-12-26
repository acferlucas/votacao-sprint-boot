package com.github.acferlucas.votacaoapispringboot.dto;

public class ResultadoVotacao {
    private long votosSim;
    private long votosNao;

    public ResultadoVotacao(long votosSim, long votosNao) {
        this.votosSim = votosSim;
        this.votosNao = votosNao;
    }

    public long getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(long votosSim) {
        this.votosSim = votosSim;
    }

    public long getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(long votosNao) {
        this.votosNao = votosNao;
    }
}
