package repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.Usuario;
import repository.interfaces.IUsuarioRepository;

@Stateless
public class UsuarioRepository implements IUsuarioRepository {

	@PersistenceContext(name = "desafio-pitang")
	private EntityManager entityManager;

	@Override
	public void salvar(Usuario usuario) {
		this.entityManager.persist(usuario);
		this.entityManager.flush();
	}

	@Override
	public List<Usuario> pegarTodos() {
		TypedQuery<Usuario> typedQuery = this.entityManager.createNamedQuery("Usuario.pegarTodos", Usuario.class);

		return typedQuery.getResultList();
	}

	@Override
	public Usuario pegarPorId(Integer id) {
		return this.entityManager.find(Usuario.class, id);
	}

	@Override
	public void removerPorId(Integer id) {
		this.entityManager.remove(pegarPorId(id));
		this.entityManager.flush();
	}

	@Override
	public void atualizar(Usuario usuario) {
		this.entityManager.merge(usuario);
		this.entityManager.flush();
	}

	@Override
	public Usuario pegarPorEmailESenha(String email, String senha) {
		try {
			TypedQuery<Usuario> typedQuery = this.entityManager.createNamedQuery("Usuario.pegarPorEmailESenha",
					Usuario.class);

			typedQuery.setParameter("email", email);
			typedQuery.setParameter("senha", senha);

			return typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
