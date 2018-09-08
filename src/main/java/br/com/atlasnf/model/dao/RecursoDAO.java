package br.com.atlasnf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.atlasnf.model.Recurso;
import br.com.atlasnf.model.RetRecurso;

@Named
@RequestScoped
public class RecursoDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Recurso> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Recurso>(this.em, Recurso.class);
	}

	@Inject
	private EntityManager em;

	public void adiciona(Recurso t) {
		dao.adiciona(t);
	}

	public void atualiza(Recurso t) {
		dao.atualiza(t);
	}

	public Recurso buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public void remove(Recurso t) {
		dao.remove(t);
	}

	public List<Recurso> listaTodos() {
		return dao.listaTodos();
	}

	public List<Recurso> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public String toString() {
		return dao.toString();
	}
	
	public List<Recurso> listaTodosPorMaisVotados(){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Recurso> cq = cb.createQuery(Recurso.class);
			Root<Recurso> r = cq.from(Recurso.class);
			cq.select(r);
			cq.orderBy(cb.desc(cb.size(r.<Collection>get("votos"))));
			return em.createQuery(cq).getResultList();
	}
	
	public List<RetRecurso> retListaRecursosPorMaisVotadados(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recurso> cq = cb.createQuery(Recurso.class);
		Root<Recurso> r = cq.from(Recurso.class);
		cq.select(r);
		cq.orderBy(cb.desc(cb.size(r.<Collection>get("votos"))));
		List<Recurso> recursos = em.createQuery(cq).getResultList();
		List<RetRecurso> retRecursos = new ArrayList<>();
		for(int i = 0; i < recursos.size(); i++) {
			Recurso recurso = recursos.get(i);
			retRecursos.add(new RetRecurso(recurso.getId(),recurso.getNome(), recurso.getDescricao(), recurso.getVotos().size()));	
		}
		return  retRecursos;
}
	
	public List<RetRecurso> retListaRecursos(){
		List<Recurso> recursos = dao.listaTodos();
		List<RetRecurso> retRecursos = new ArrayList<>();
		for(int i = 0; i < recursos.size(); i++) {
			Recurso recurso = recursos.get(i);
			retRecursos.add(new RetRecurso(recurso.getId(),recurso.getNome(), recurso.getDescricao(), recurso.getVotos().size()));	
		}
		return retRecursos;
		
	}
	
	
}
