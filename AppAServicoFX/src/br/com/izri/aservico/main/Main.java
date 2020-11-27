package br.com.izri.aservico.main;

import java.util.Scanner;

import br.com.izri.aservico.controller.FXMLTelaLoginController;
import br.com.izri.aservico.controller.FXMLTelaPrincipalController;
import br.com.izri.aservico.controller.base.ControllerBase;
import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.Usuario;
import br.com.izri.aservico.model.entity.dao.CelulaDAO;
import br.com.izri.aservico.model.entity.dao.MembroDAO;
import br.com.izri.aservico.model.entity.dao.TurmaEscolaBiblicaDAO;
import br.com.izri.aservico.model.entity.dao.UsuarioDAO;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends ControllerBase {

	public void salvarUsuario() {
		new UsuarioDAO().salvarUsuarioTeste();
	}

	public void salvarTurma(String nomeTurma) {
		new TurmaEscolaBiblicaDAO().salvarTurmaTeste(nomeTurma);
	}

	public void salvarCelula(String nomeCelula) {
		new CelulaDAO().salvarCelulaTeste(nomeCelula);
	}

	public void salvarMembroTeste(String posicao) {
		new MembroDAO().salvarMembroTeste(posicao);
	}

	public static void main(String[] args) throws Exception {
		Main.principal();
	}

	public static void principal() {
		String escolha = "";

		do {
			System.out.println("1 - Salvar Usuário");
			System.out.println("2 - Salvar Célula");
			System.out.println("3 - Salvar Turma Escola Bíblica");
			System.out.println("4 - Salvar Membro Teste");
			System.out.println("5 - Iniciar Tela Login");
			System.out.println("6 - Iniciar Tela Principal");
			System.out.println("7 - Gerar teste PDF");
			System.out.println("8 - Sair");
			System.out.println("--------------------------------------------------------");
			System.out.print("Entre com a opção: ");

			Scanner entrada = new Scanner(System.in);
			int opcao = entrada.nextInt();
			String nomeEntrada = "";

			String posicao = "";

			if (opcao == 1) {
				new Main().salvarUsuario();
				System.out.println("Deseja continuar? (sim/nao)");
				escolha = entrada.next();
			} else if (opcao == 2) {
				System.out.print("Entre com o nome da Célula: ");
				nomeEntrada = entrada.next();
				new Main().salvarCelula(nomeEntrada);
				System.out.println("Deseja continuar? (sim/nao)");
				escolha = entrada.next();
			} else if (opcao == 3) {
				System.out.print("Entre com o nome da Turma: ");
				nomeEntrada = entrada.next();
				new Main().salvarTurma(nomeEntrada);
				System.out.println("Deseja continuar? (sim/nao)");
				escolha = entrada.next();
			} else if (opcao == 4) {
				System.out.println("Entre com a posicao: ");
				posicao = entrada.next();
				new Main().salvarMembroTeste(posicao);
				System.out.println("Deseja continuar? (sim/nao)");
				escolha = entrada.next();
			} else if (opcao == 5) {
				Application.launch(FXMLTelaLoginController.class);
			} else if (opcao == 6) {
				new MembroDAO().getEntityTransaction().begin();
				Usuario usuario = new UsuarioDAO().getUsuarioPorLogin("izrimiranda");
				UsuarioSessao.getInstance().setUsuario(usuario);
				Application.launch(FXMLTelaPrincipalController.class);
			} else if (opcao == 7) {
				//
			} else if (opcao == 8) {
				System.out.println("Sessão Finalizada.");
				System.exit(0);
			}

		} while (escolha != "nao");

		System.exit(0);

		// new Main().salvarUsuario();
		// new UsuarioDAO().iniciarBanco();
		// Application.launch(FXMLTelaLoginController.class);
		// new Main().salvarTurma();
		// Application.launch(FXMLTelaPrincipalController.class);
		// new Main().salvarCelula();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// INSERINDO ICONE
		// primaryStage.getIcons().add(new
		// Image(Main.class.getResourceAsStream("/imagens/Logo-Igreja-SISTEMAS.png")));

		// Pane telaLogin =
		// FXMLLoader.load(this.getClass().getResource("/br/com/izri/aservico/telas/FXMLTelaLogin.fxml"));

		// Scene scene = new Scene(telaLogin, 800, 600);

		// primaryStage.setScene(scene);

		// primaryStage.show();
	}

	@Override
	public void fecharTela() {
		// TODO Auto-generated method stub

	}
}
