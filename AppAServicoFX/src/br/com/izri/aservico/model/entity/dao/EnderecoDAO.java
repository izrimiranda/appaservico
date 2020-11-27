package br.com.izri.aservico.model.entity.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.izri.aservico.model.entity.Endereco;
import br.com.izri.aservico.model.entity.entitymanager.EntityManagerUtils;

public class EnderecoDAO extends EntityManagerUtils<Endereco> {

	public List<Endereco> buscarTodos() {
		TypedQuery<Endereco> find = null;
		List<Endereco> listaRetorno = null;

		try {
			find = this.getEntityManager().createNamedQuery(Endereco.FIND_ALL, Endereco.class);
			find.setParameter("excluido", false);

			listaRetorno = find.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaRetorno;
	}
}
