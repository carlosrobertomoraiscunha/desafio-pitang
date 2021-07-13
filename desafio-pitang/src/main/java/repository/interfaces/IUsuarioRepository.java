package repository.interfaces;

import models.Usuario;

public interface IUsuarioRepository extends IRepository<Usuario, Integer> {
	public Usuario pegarPorEmailESenha(String email, String senha);
}
