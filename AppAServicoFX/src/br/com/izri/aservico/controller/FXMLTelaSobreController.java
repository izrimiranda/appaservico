package br.com.izri.aservico.controller;

import br.com.izri.aservico.controller.base.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLTelaSobreController extends ControllerBase {

	@FXML
	private Label idLogoSobre;

	@FXML
	private AnchorPane idTelaSobre;

	@Override
	public void fecharTela() {
	}

	@Override
	public void start(Stage stage) throws Exception {

		Pane telaPrincipal = FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/FXMLTelaSobre.fxml"));

		stage.setResizable(false);

		stage.getIcons().add(new Image("/imagens/icone.png"));

		Scene scene = new Scene(telaPrincipal, 1280, 700);

		stage.setScene(scene);

		stage.show();

	}

	@FXML
	public void fecharTelaAcao(ActionEvent event) {
		Stage stage = (Stage) this.idTelaSobre.getScene().getWindow();
		stage.close();
	}

}
