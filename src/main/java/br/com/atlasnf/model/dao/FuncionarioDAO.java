package br.com.atlasnf.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.RetFuncionario;

@Named
@RequestScoped
public class FuncionarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Funcionario> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Funcionario>(this.em, Funcionario.class);
	}

	@Inject
	private EntityManager em;

	public void adiciona(Funcionario t) {
		dao.adiciona(t);
	}

	public void atualiza(Funcionario t) {
		dao.atualiza(t);
	}

	public Funcionario buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	public void remove(Funcionario t) {
		dao.remove(t);
	}

	public List<Funcionario> listaTodos() {
		return dao.listaTodos();
	}

	public List<Funcionario> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public String toString() {
		return dao.toString();
	}

	public Funcionario executarlogin(String email, String senha) {
		TypedQuery<Funcionario> query = em.createQuery(
				"SELECT f FROM  Funcionario f where f.email = :pEmail and f.senha = :pSenha", Funcionario.class);

		query.setParameter("pEmail", email);
		query.setParameter("pSenha", senha);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Funcionario buscarFuncionarioPorEmail(String email) {
		TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM  Funcionario f where f.email = :pEmail",
				Funcionario.class);

		query.setParameter("pEmail", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<RetFuncionario> getAllRetFuncionarios() {
		List<Funcionario> funcionarios = listaTodos();
		List<RetFuncionario> retFuncionarios = new ArrayList<>();
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario aux = funcionarios.get(i);
			retFuncionarios.add(new RetFuncionario(aux.getId(), aux.getNome(), aux.getEmail(), aux.getVotos().size()));

		}
		return retFuncionarios;
	}

}
