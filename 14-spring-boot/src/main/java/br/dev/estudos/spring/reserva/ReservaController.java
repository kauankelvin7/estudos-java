package br.dev.estudos.spring.reserva;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService servico;
    public ReservaController(ReservaService servico) { this.servico = servico; }

    @PostMapping
    public ResponseEntity<ReservaResponse> criar(@Valid @RequestBody CriarReservaRequest pedido) {
        var criada = servico.criar(pedido);
        return ResponseEntity.created(URI.create("/api/reservas/" + criada.id())).body(criada);
    }
    @GetMapping
    public List<ReservaResponse> listar() { return servico.listar(); }
    @GetMapping("/{id}")
    public ReservaResponse buscar(@PathVariable("id") long id) { return servico.buscar(id); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable("id") long id) { servico.cancelar(id); }
}
