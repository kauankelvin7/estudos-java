package br.dev.estudos.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** A interface reage aos eventos; as regras ficam no QuadroTarefas. */
public final class TarefasApp extends Application {
    private final QuadroTarefas quadro = new QuadroTarefas();
    private final ListView<Tarefa> lista = new ListView<>();

    @Override public void start(Stage palco) {
        TextField entrada = new TextField();
        entrada.setPromptText("Descreva uma tarefa...");
        HBox.setHgrow(entrada, javafx.scene.layout.Priority.ALWAYS);

        Button adicionar = new Button("Adicionar");
        adicionar.setDefaultButton(true);
        adicionar.setOnAction(evento -> executar(() -> {
            quadro.adicionar(entrada.getText());
            entrada.clear();
            atualizar();
        }));
        Button concluir = new Button("Concluir selecionada");
        concluir.setOnAction(evento -> executar(() -> {
            Tarefa selecionada = lista.getSelectionModel().getSelectedItem();
            if (selecionada == null) throw new IllegalArgumentException("Selecione uma tarefa");
            quadro.concluir(selecionada.id());
            atualizar();
        }));
        var conteudo = new VBox(12, new HBox(8, entrada, adicionar), lista, concluir);
        conteudo.setPadding(new Insets(18));
        conteudo.getStyleClass().add("janela");
        var cena = new Scene(conteudo, 540, 420);
        cena.getStylesheets().add(getClass().getResource("/estilo.css").toExternalForm());
        palco.setTitle("Quadro de tarefas");
        palco.setScene(cena);
        palco.show();
    }
    private void atualizar() { lista.getItems().setAll(quadro.listar()); }
    private void executar(Runnable acao) {
        try { acao.run(); }
        catch (IllegalArgumentException erro) {
            new Alert(Alert.AlertType.WARNING, erro.getMessage()).showAndWait();
        }
    }
    public static void main(String[] args) { launch(args); }
}
