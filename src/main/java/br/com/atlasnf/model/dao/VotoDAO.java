package br.com.atlasnf.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.Recurso;
import br.com.atlasnf.model.Voto;

@Named
@RequestScoped
public class VotoDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Voto> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Voto>(this.em, Voto.class);
	}

	@Inject
	private EntityManager em;

	public void adiciona(Voto t) {
		dao.adiciona(t);
	}

	public void atualiza(Voto t) {
		dao.atualiza(t);
	}

	public Voto buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public void remove(Voto t) {
		dao.remove(t);
	}

	public List<Voto> listaTodos() {
		return dao.listaTodos();
	}

	public List<Voto> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public String toString() {
		return dao.toString();
	}

	public boolean buscarVotoFuncERecurso(Funcionario funcionario, Recurso recursoSelected) {
		TypedQuery<Voto> query = em.createQuery(
				"SELECT v FROM  Voto v where v.funcionario = :pIdFuncionario and v.recurso = :pIdRecurso", Voto.class);

		query.setParameter("pIdFuncionario", funcionario);
		query.setParameter("pIdRecurso", recursoSelected);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public List<Voto> buscarVotoFunc(Funcionario funcionario) {
		TypedQuery<Voto> query = em.createQuery(
				"SELECT v FROM  Voto v where v.funcionario = :pIdFuncionario", Voto.class);

		query.setParameter("pIdFuncionario", funcionario);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}


}
