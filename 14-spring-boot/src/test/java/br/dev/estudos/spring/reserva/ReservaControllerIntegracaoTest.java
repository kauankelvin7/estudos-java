package br.dev.estudos.spring.reserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaControllerIntegracaoTest {
    @Autowired MockMvc http;
    @Autowired ReservaRepository repositorio;

    @BeforeEach void limpar() { repositorio.deleteAll(); }

    private String requisicao(String sala, String inicio, String fim) {
        return """
                {"sala":"%s","responsavel":"Ana","inicio":"%s","fim":"%s"}
                """.formatted(sala, inicio, fim);
    }

    @Test void cadastraConsultaERejeitaChoqueDeHorario() throws Exception {
        var corpo = requisicao("Sala 3", "2030-05-02T09:00:00", "2030-05-02T10:00:00");
        http.perform(post("/api/reservas").contentType(MediaType.APPLICATION_JSON).content(corpo))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.sala").value("Sala 3"));
        http.perform(get("/api/reservas")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].responsavel").value("Ana"));
        http.perform(post("/api/reservas").contentType(MediaType.APPLICATION_JSON)
                .content(requisicao("sala 3", "2030-05-02T09:30:00", "2030-05-02T10:30:00")))
                .andExpect(status().isConflict()).andExpect(jsonPath("$.erro").value("CONFLITO"));
    }
    @Test void validaEntradaENaoEncontrado() throws Exception {
        http.perform(post("/api/reservas").contentType(MediaType.APPLICATION_JSON)
                .content(requisicao("", "2030-05-02T09:00:00", "2030-05-02T10:00:00")))
                .andExpect(status().isBadRequest());
        http.perform(get("/api/reservas/999999")).andExpect(status().isNotFound());
    }
    @Test void aceitaHorariosAdjacentes() throws Exception {
        http.perform(post("/api/reservas").contentType(MediaType.APPLICATION_JSON)
                .content(requisicao("Sala A", "2030-05-02T09:00:00", "2030-05-02T10:00:00")))
                .andExpect(status().isCreated());
        http.perform(post("/api/reservas").contentType(MediaType.APPLICATION_JSON)
                .content(requisicao("Sala A", "2030-05-02T10:00:00", "2030-05-02T11:00:00")))
                .andExpect(status().isCreated());
    }
}
