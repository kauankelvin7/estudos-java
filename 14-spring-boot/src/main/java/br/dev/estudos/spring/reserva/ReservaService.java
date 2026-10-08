package br.dev.estudos.spring.reserva;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository repositorio;
    public ReservaService(ReservaRepository repositorio) { this.repositorio = repositorio; }

    @Transactional
    public ReservaResponse criar(CriarReservaRequest pedido) {
        // A entidade mantém a regra de intervalo válido, mesmo fora da API HTTP.
        Reserva reserva = new Reserva(pedido.sala(), pedido.responsavel(), pedido.inicio(), pedido.fim());
        if (repositorio.existsBySalaIgnoreCaseAndInicioLessThanAndFimGreaterThan(
                reserva.getSala(), reserva.getFim(), reserva.getInicio()))
            throw new ConflitoReservaException(reserva.getSala());
        return ReservaResponse.de(repositorio.save(reserva));
    }

    @Transactional(readOnly = true)
    public List<ReservaResponse> listar() {
        return repositorio.findAll().stream().map(ReservaResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public ReservaResponse buscar(long id) {
        return ReservaResponse.de(repositorio.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException(id)));
    }

    @Transactional
    public void cancelar(long id) {
        if (!repositorio.existsById(id)) throw new ReservaNaoEncontradaException(id);
        repositorio.deleteById(id);
    }
}
