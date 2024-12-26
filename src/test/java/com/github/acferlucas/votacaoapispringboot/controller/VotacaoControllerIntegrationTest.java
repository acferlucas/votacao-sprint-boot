package com.github.acferlucas.votacaoapispringboot.controller;

import com.github.acferlucas.votacaoapispringboot.config.TestContainersConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestContainersConfig.class})
public class VotacaoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostgreSQLContainer<?> postgresContainer;

    @Test
    public void testCreateVotacao() throws Exception {
        String requestBody = """
                {
                    "titulo": "Votação 1",
                    "descricao": "Descrição da votação 1"
                }
                """;

        mockMvc.perform(post("/api/votacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllVotacoes() throws Exception {
        mockMvc.perform(get("/api/votacoes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
