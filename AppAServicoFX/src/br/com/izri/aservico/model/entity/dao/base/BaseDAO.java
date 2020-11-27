/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.izri.aservico.model.entity.dao.base;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Izri Miranda
 */
public abstract class BaseDAO {

	public static final String LIKE_CLAUSE_PATTERN = "%%%s%%";

	EntityManager em;

	public BaseDAO() {

	}

	public BaseDAO(EntityManager em) {
		this.em = em;
	}

	protected final Query createQuery(String sql) {
		return this.createQuery(sql, new HashMap<String, Object>());
	}

	protected final Query createQuery(String sql, Map<String, Object> params) {
		final Query q = this.em.createQuery(sql);

		this.preencheMapaParametrosQuery(params, q);

		return q;
	}

	public void preencheMapaParametrosQuery(Map<String, Object> params, Query q) {
		if (params != null) {
			for (final String nome : params.keySet()) {
				q.setParameter(nome, params.get(nome));
			}
		}
	}

}
