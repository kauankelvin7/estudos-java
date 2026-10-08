package br.dev.estudos.spring.reserva;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public final class ApiExceptionHandler {
    @ExceptionHandler(ConflitoReservaException.class)
    public ResponseEntity<Map<String, String>> conflito(ConflitoReservaException erro) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", "CONFLITO", "mensagem", erro.getMessage()));
    }
    @ExceptionHandler(ReservaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> ausente(ReservaNaoEncontradaException erro) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "NAO_ENCONTRADO", "mensagem", erro.getMessage()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> regraInvalida(IllegalArgumentException erro) {
        return ResponseEntity.badRequest().body(Map.of("erro", "DADOS_INVALIDOS", "mensagem", erro.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacao(MethodArgumentNotValidException erro) {
        String campo = erro.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField()).findFirst().orElse("requisição");
        return ResponseEntity.badRequest().body(Map.of("erro", "VALIDACAO", "mensagem", "Campo inválido: " + campo));
    }
}
