package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import models.Telefone;
import models.Usuario;
import repository.interfaces.IUsuarioRepository;

@Named(value = "cadastroUsuarioMB")
@SessionScoped
public class CadastroUsuarioMB implements Serializable {

	private static final long serialVersionUID = 7689624636764343378L;

	@EJB
	private IUsuarioRepository usuarioRepository;

	private Usuario usuarioCadastro;
	private List<Telefone> telefones;

	private String numeroTelefone;
	private String tipoTelefone;

	@PostConstruct
	public void configurar() {
		this.usuarioCadastro = new Usuario();
		this.telefones = new ArrayList<Telefone>();
	}

	public String cadastrar() {
		this.usuarioRepository.salvar(usuarioCadastro);

		limparCampos();

		return "/index";
	}

	public void adicionarTelefone() {
		Telefone telefone = new Telefone();

		telefone.setDdd(Integer.parseInt(this.numeroTelefone.substring(1, 3)));
		telefone.setNumero(this.numeroTelefone.split(" ")[1]);
		telefone.setTipo(this.tipoTelefone);

		this.telefones.add(telefone);
		this.usuarioCadastro.setTelefones(telefones);

		this.numeroTelefone = "";
		this.tipoTelefone = "";
	}

	private void limparCampos() {
		this.usuarioCadastro = new Usuario();
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}
