package br.com.izri.aservico.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AcaoBotoesPrincipais {

	private static AcaoBotoesPrincipais instance;

	public static AcaoBotoesPrincipais getInstance() {
		if (AcaoBotoesPrincipais.instance == null) {
			AcaoBotoesPrincipais.instance = new AcaoBotoesPrincipais();
		}

		return AcaoBotoesPrincipais.instance;
	}

	private AcaoBotoesPrincipais() {

	}

	public void ativarImagemBotaoMembrosMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/botao_membros_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosCadastrarPressed(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_membros_pressed.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosCadastrarMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_membros_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosCadastrarNormal(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_membros.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosConsultarPressed(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_membros_pressed.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosConsultarMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_membros_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosConsultarNormal(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_membros.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaCadastrarPressed(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_secretaria_pressed.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaCadastrarMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_secretaria_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaCadastrarNormal(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_secretaria.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaConsultarPressed(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_secretaria_pressed.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaConsultarMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_secretaria_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaConsultarNormal(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_secretaria.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaCadastrarPressed(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_tesouraria_pressed.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaCadastrarMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_tesouraria_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaCadastrarNormal(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_cadastrar_tesouraria.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaConsultarPressed(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_tesouraria_pressed.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaConsultarMouseOver(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_tesouraria_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaConsultarNormal(ImageView botao) {
		Image image = new Image(AcaoBotoesPrincipais.class.getClass().getResource("/imagens/sub_botao_consultar_tesouraria.png").toExternalForm());

		botao.setImage(image);
	}

	// BOTOES PRINCIPAIS
	public void ativarImagemBotaoInicioNormal(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_inicio_normal.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoInicioMouseOver(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_inicio_mouse_over.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoInicioPressed(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_inicio_pressed.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoMembrosNormal(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_membros_normal.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaMouseOver(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_secretaria_mouse_over.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSecretariaNormal(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_secretaria_normal.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaMouseOver(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_tesouraria_mouse_over.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoTesourariaNormal(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_tesouraria_normal.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSistemaMouseOver(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_sistema_mouse_over.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSistemaNormal(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_sistema_normal.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSistemaPressed(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botao_sistema_pressed.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSairNormal(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botoes_gerais/botao_sair_normal.png").toExternalForm());
		botao.setImage(image);
	}

	public void ativarImagemBotaoSairMouseOver(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botoes_gerais/botao_sair_mouse_over.png").toExternalForm());

		botao.setImage(image);
	}

	public void ativarImagemBotaoSairPressed(ImageView botao) {
		Image image = new Image(this.getClass().getResource("/imagens/botoes_gerais/botao_sair_mouse_pressed.png").toExternalForm());
		botao.setImage(image);
	}
}
