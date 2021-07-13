package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
@NamedQueries(value = { @NamedQuery(name = "Usuario.pegarTodos", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.pegarPorEmailESenha", query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha") })
public class Usuario extends EntidadeBase {

	@NotBlank(message = "O nome é obrigatório!")
	@Column(name = "nome")
	private String nome;

	@Email(message = "O email é inválido!")
	@NotBlank(message = "O email é obrigatório!")
	@Column(name = "email", unique = true)
	private String email;

	@NotBlank(message = "A senha é obrigatória!")
	@Column(name = "senha")
	private String senha;

	@Column(name = "ativo")
	private boolean ativo;

	@Valid
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones;

	public Usuario() {
		this.telefones = new ArrayList<>();
		this.ativo = true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		for (Telefone telefone : telefones)
			telefone.setUsuario(this);

		this.telefones = telefones;
	}

	public void addTelefone(Telefone telefone) {
		telefone.setUsuario(this);
		this.telefones.add(telefone);
	}

	public void removeTelefone(Telefone telefone) {
		this.telefones.remove(telefone);
	}
}
