package br.com.izri.aservico.controller.sistema;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.izri.aservico.controller.base.ControllerBase;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class FXMLTabSistemaCadastrarController extends ControllerBase implements Initializable {

	private static FXMLTabSistemaCadastrarController instance;

	public static FXMLTabSistemaCadastrarController getInstance() {
		if (FXMLTabSistemaCadastrarController.instance == null) {
			FXMLTabSistemaCadastrarController.instance = new FXMLTabSistemaCadastrarController();
		}

		return FXMLTabSistemaCadastrarController.instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fecharTela() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
