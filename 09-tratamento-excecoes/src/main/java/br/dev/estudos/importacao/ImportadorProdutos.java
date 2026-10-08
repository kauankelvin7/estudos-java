package br.dev.estudos.importacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Formato didático: sku;preco, uma linha por produto (sem aspas ou delimitadores escapados). */
public final class ImportadorProdutos {
    public record ProdutoImportado(String sku, BigDecimal preco) { }

    public List<ProdutoImportado> importar(Path arquivo) throws IOException {
        List<ProdutoImportado> produtos = new ArrayList<>();
        try (BufferedReader leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            String linha;
            int numero = 0;
            while ((linha = leitor.readLine()) != null) {
                numero++;
                if (linha.isBlank() || linha.startsWith("#")) continue;
                String[] colunas = linha.split(";", -1);
                if (colunas.length != 2 || colunas[0].isBlank())
                    throw new LinhaInvalidaException(numero, "esperado sku;preco", null);
                try {
                    BigDecimal preco = new BigDecimal(colunas[1].trim());
                    if (preco.signum() < 0) throw new NumberFormatException("preço negativo");
                    produtos.add(new ProdutoImportado(colunas[0].trim(), preco));
                } catch (NumberFormatException causa) {
                    throw new LinhaInvalidaException(numero, "preço inválido", causa);
                }
            }
        }
        return List.copyOf(produtos);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) { System.err.println("Uso: caminho/para/produtos.csv"); return; }
        System.out.println(new ImportadorProdutos().importar(Path.of(args[0])));
    }
}
