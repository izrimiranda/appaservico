package br.com.izri.aservico.controller.base;

import br.com.izri.aservico.auxiliar.PopupDialog;
import br.com.izri.aservico.model.entity.dao.CelulaDAO;
import br.com.izri.aservico.model.entity.dao.DizimoDAO;
import br.com.izri.aservico.model.entity.dao.EnderecoDAO;
import br.com.izri.aservico.model.entity.dao.HistoricoEventosSistemaDAO;
import br.com.izri.aservico.model.entity.dao.MembroDAO;
import br.com.izri.aservico.model.entity.dao.OfertaDAO;
import br.com.izri.aservico.model.entity.dao.RelatorioSessaoDAO;
import br.com.izri.aservico.model.entity.dao.TransacaoSaidaDAO;
import br.com.izri.aservico.model.entity.dao.TurmaEscolaBiblicaDAO;
import br.com.izri.aservico.model.entity.dao.UsuarioDAO;
import javafx.application.Application;

public abstract class ControllerBase extends Application {

	public abstract void fecharTela();

	private PopupDialog popupDialog;

	private UsuarioDAO usuarioDAO;
	private HistoricoEventosSistemaDAO historicoEventosDAO;
	private MembroDAO membroDAO;
	private CelulaDAO celulaDAO;
	private DizimoDAO dizimoDAO;
	private OfertaDAO ofertaDAO;
	private TransacaoSaidaDAO transacaoSaidaDAO;
	private RelatorioSessaoDAO relatorioSessaoDAO;
	private TurmaEscolaBiblicaDAO turmaEscolaBiblicaDAO;
	private EnderecoDAO enderecoDAO;

	public RelatorioSessaoDAO getRelatorioSessaoDAO() {
		if (this.relatorioSessaoDAO == null) {
			this.relatorioSessaoDAO = new RelatorioSessaoDAO();
		}

		return this.relatorioSessaoDAO;
	}

	public void setRelatorioSessaoDAO(RelatorioSessaoDAO relatorioSessaoDAO) {
		this.relatorioSessaoDAO = relatorioSessaoDAO;
	}

	public HistoricoEventosSistemaDAO getHstoricoEventosDAO() {
		if (this.historicoEventosDAO == null) {
			this.historicoEventosDAO = new HistoricoEventosSistemaDAO();
		}
		return this.historicoEventosDAO;
	}

	public void setHistoricoEventoDAO(HistoricoEventosSistemaDAO historicoEventosDAO) {
		this.historicoEventosDAO = historicoEventosDAO;
	}

	public MembroDAO getMembroDAO() {
		if (this.membroDAO == null) {
			this.membroDAO = new MembroDAO();
		}
		return this.membroDAO;
	}

	public void setMembroDAO(MembroDAO membroDAO) {
		this.membroDAO = membroDAO;
	}

	public CelulaDAO getCelulaDAO() {
		if (this.celulaDAO == null) {
			this.celulaDAO = new CelulaDAO();
		}
		return this.celulaDAO;
	}

	public void setCelulaDAO(CelulaDAO celulaDAO) {
		this.celulaDAO = celulaDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null) {
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DizimoDAO getDizimoDAO() {
		if (this.dizimoDAO == null) {
			this.dizimoDAO = new DizimoDAO();
		}

		return this.dizimoDAO;
	}

	public OfertaDAO getOfertaDAO() {
		if (this.ofertaDAO == null) {
			this.ofertaDAO = new OfertaDAO();
		}

		return this.ofertaDAO;
	}

	public TransacaoSaidaDAO getTransacaoSaidaDAO() {
		if (this.transacaoSaidaDAO == null) {
			this.transacaoSaidaDAO = new TransacaoSaidaDAO();
		}

		return this.transacaoSaidaDAO;
	}

	public TurmaEscolaBiblicaDAO getTurmaEscolaBiblicaDAO() {
		if (this.turmaEscolaBiblicaDAO == null) {
			this.turmaEscolaBiblicaDAO = new TurmaEscolaBiblicaDAO();
		}
		return this.turmaEscolaBiblicaDAO;
	}

	public void setTurmaEscolaBiblicaDAO(TurmaEscolaBiblicaDAO turmaEscolaBiblicaDAO) {
		this.turmaEscolaBiblicaDAO = turmaEscolaBiblicaDAO;
	}

	public EnderecoDAO getEnderecoDAO() {
		if (this.enderecoDAO == null) {
			this.enderecoDAO = new EnderecoDAO();
		}
		return this.enderecoDAO;
	}

	public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}

}
