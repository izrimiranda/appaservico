package br.com.izri.aservico.model.entity.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import br.com.izri.Seguranca;
import br.com.izri.aservico.controller.session.UsuarioSessao;
import br.com.izri.aservico.model.entity.Celula;
import br.com.izri.aservico.model.entity.Usuario;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;

public class UsuarioDAO extends EntityManagerUtils<Usuario> {

	private HistoricoEventosSistemaDAO historicoEventosDAO;

	public HistoricoEventosSistemaDAO getHistoricoEventosDAO() {
		if (this.historicoEventosDAO == null) {
			this.historicoEventosDAO = new HistoricoEventosSistemaDAO();
		}

		return this.historicoEventosDAO;
	}

	public Usuario isUsuarioReadyToLogin(String login, String senha) {
		TypedQuery<Usuario> find = null;

		Logger logger = Logger.getLogger(login);

		Usuario retornoBanco = null;

		try {

			retornoBanco = new Usuario();

			login = login.toLowerCase().trim();

			find = this.getEntityManager().createNamedQuery("Usuario.findByLoginSenha", Usuario.class);

			find.setParameter("login", login);
			find.setParameter("senha", senha);

			retornoBanco = find.getSingleResult();
			this.getHistoricoEventosDAO().salvarEventoSistema(UsuarioSessao.getInstance().getUsuario(), "Login feito no sistema.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

	public void salvarUsuarioTeste() {
		Celula celula = new Celula();

		Usuario usuario = new Usuario();

		usuario.setCelulaLiderada(celula);

		usuario.setLogin("izrimiranda");
		usuario.setSenha(Seguranca.convertStringToMd5("root"));
		usuario.setEmail("usuarioteste@outlook.com");
		usuario.setNomeCompleto("Izri Bruno Miranda");
		usuario.setExcluido(false);
		usuario.setDataCadastro(Calendar.getInstance());

		System.out.println("@@@@@ Salvando usuário!");
		this.getEntityTransaction().begin();
		this.getEntityManager().persist(usuario);
		this.getEntityTransaction().commit();
		this.getEntityManager().close();

		System.out.println("Usuário salvo com sucesso!!!!");
	}

	public Usuario isEmailValido(String email) {
		Usuario usuario = null;
		Usuario retornoBanco = null;
		TypedQuery<Usuario> find = null;

		try {
			usuario = new Usuario();
			retornoBanco = new Usuario();
			usuario.setEmail(email);

			find = this.getEntityManager().createNamedQuery(Usuario.FIND_BY_EMAIL, Usuario.class);

			find.setParameter("email", email);
			find.setParameter("excluido", false);

			retornoBanco = find.getSingleResult();

		} catch (Exception e) {
			return null;
		}

		return retornoBanco;
	}

	public void persistOrUpdate(Usuario usuario) {
		Map<String, Object> params = new HashMap<>();
		// Query query = null;

		try {
			if (usuario.getId() > 0) {
				this.begin();
				StringBuilder query = new StringBuilder("UPDATE Usuario u SET u.senha = :senha WHERE u.id = :id ");
				params.put("senha", usuario.getSenha());
				params.put("id", usuario.getId());
				int execute = this.createQuery(query.toString(), params).executeUpdate();
				System.out.println(execute);
				this.getEntityTransaction().commit();
			} else {
				this.getEntityManager().persist(usuario);
				this.getEntityTransaction().commit();
			}
			this.getEntityManager().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioPorLogin(String login) {

		Usuario usuarioRetorno = null;
		TypedQuery<Usuario> find = null;

		try {
			usuarioRetorno = new Usuario();
			find = this.getEntityManager().createNamedQuery(Usuario.FIND_BY_LOGIN, Usuario.class);
			find.setParameter("login", login);
			find.setParameter("excluido", false);
			usuarioRetorno = find.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return usuarioRetorno;
	}

	public void testeConexao() {
		this.getEntityManager();
	}

	public void iniciarBanco() {
		this.getEntityTransaction().begin();
	}
}
