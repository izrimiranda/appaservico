package br.com.izri.aservico.model.entity.entitymanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class EntityManagerUtils<T> {

	@PersistenceContext(unitName = "gestao")
	private EntityManager em = null;
	private EntityManagerFactory factory = null;

	public EntityManagerUtils() {
		this.em = this.getEntityManagerFactory().createEntityManager();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if (this.factory == null) {
			this.factory = Persistence.createEntityManagerFactory("gestao");
		}

		return this.factory;
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	public EntityTransaction getEntityTransaction() {
		final EntityTransaction et = this.em.getTransaction();
		return et;
	}

	public void begin() {
		this.getEntityTransaction().begin();
	}

	public void commit() {
		this.getEntityTransaction().commit();
	}

	public void close() {
		this.getEntityTransaction();
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

	@SuppressWarnings("unchecked")
	public List<T> getResultList(String query) {
		return this.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> getResultList(String query, Map<String, Object> params) {
		return this.createQuery(query, params).getResultList();
	}
}
