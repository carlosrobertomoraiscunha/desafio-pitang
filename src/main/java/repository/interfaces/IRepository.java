package repository.interfaces;

import java.util.List;

import models.EntidadeBase;

public interface IRepository<TEntity extends EntidadeBase, TId> {

	public void salvar(TEntity entidade);
	
	public List<TEntity> pegarTodos();
	
	public TEntity pegarPorId(TId id);
	
	public void removerPorId(TId id);
	
	public void atualizar(TEntity entidade);
}
