package br.dev.estudos.importacao;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

class ImportadorProdutosTest {
    @TempDir Path pasta;
    @Test void importaConteudoValido() throws Exception {
        var arquivo = Files.writeString(pasta.resolve("valido.csv"), "# comentário\nLIV-1;19.90\n\nLIV-2;10\n");
        assertEquals(2, new ImportadorProdutos().importar(arquivo).size());
    }
    @Test void informaLinhaEPreservaCausa() throws Exception {
        var arquivo = Files.writeString(pasta.resolve("invalido.csv"), "A;10\nB;dez\n");
        var erro = assertThrows(LinhaInvalidaException.class, () -> new ImportadorProdutos().importar(arquivo));
        assertTrue(erro.getMessage().contains("Linha 2"));
        assertNotNull(erro.getCause());
    }
}
