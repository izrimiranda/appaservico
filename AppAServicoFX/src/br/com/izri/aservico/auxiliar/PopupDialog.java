package br.com.izri.aservico.auxiliar;

import br.com.izri.aservico.main.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PopupDialog {

	private PopupDialog() {

	}

	private static PopupDialog instance;

	public static PopupDialog getInstance() {
		if (PopupDialog.instance == null) {
			PopupDialog.instance = new PopupDialog();
		}

		return PopupDialog.instance;
	}

	public void exibirPopupInformacao(String mensagem, String titulo) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);

		alerta.setHeaderText(mensagem);
		alerta.setTitle(titulo);

		Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

		stage.getIcons().add(new Image(Main.class.getResourceAsStream("/imagens/icone.png")));

		alerta.showAndWait();
	}

	public boolean isConfirmacaoOk(String mensagem, String titulo) {

		boolean retorno = false;

		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

		alerta.setHeaderText(mensagem);
		alerta.setTitle(titulo);

		Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

		stage.getIcons().add(new Image(Main.class.getResourceAsStream("/imagens/icone.png")));

		alerta.showAndWait();

		if (alerta.getResult() == ButtonType.OK) {
			retorno = true;
		}

		return retorno;

	}

	public void exibirPopupErro(String mensagem, String titulo) {
		Alert alerta = new Alert(Alert.AlertType.ERROR);

		alerta.setHeaderText(mensagem);
		alerta.setTitle(titulo);

		Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

		stage.getIcons().add(new Image(Main.class.getResourceAsStream("/imagens/icone.png")));

		alerta.showAndWait();
	}

	public void exibirPopupEsqueceuSenha(String mensagem, String titulo) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);

		alerta.setHeaderText(mensagem);
		alerta.setTitle(titulo);

		Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

		stage.getIcons().add(new Image(Main.class.getResourceAsStream("/imagens/icone.png")));

		alerta.showAndWait();
	}

	public void exibirPopupSucesso(String mensagem, String titulo) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);

		alerta.setHeaderText(mensagem);
		alerta.setTitle(titulo);

		Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

		stage.getIcons().add(new Image(Main.class.getResourceAsStream("/imagens/icone.png")));

		alerta.showAndWait();
	}

}
