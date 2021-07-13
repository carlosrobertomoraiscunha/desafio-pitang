package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "telefones")
public class Telefone extends EntidadeBase {

	@Min(1)
	@Column(name = "ddd")
	private Integer ddd;

	@NotBlank
	@Column(name = "numero")
	private String numero;

	@NotBlank
	@Column(name = "tipo")
	private String tipo;

	@Valid
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Telefone() {
		this.usuario = new Usuario();
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.tipo).append(" - ");
		sb.append("(").append(this.ddd).append(") ");
		sb.append(this.numero);

		return sb.toString();
	}
}
