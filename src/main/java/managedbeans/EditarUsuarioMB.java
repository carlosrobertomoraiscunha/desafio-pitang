package managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import models.Telefone;
import models.Usuario;
import repository.interfaces.IUsuarioRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named(value = "editarUsuarioMB")
@SessionScoped
public class EditarUsuarioMB implements Serializable {

	private static final long serialVersionUID = -2184776904706214504L;

	@EJB
	private IUsuarioRepository usuarioRepository;

	private Usuario usuario;
	private List<Telefone> telefones;

	private String numeroTelefone;
	private String tipoTelefone;

	@PostConstruct
	public void configurar() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		this.usuario = (Usuario) sessionMap.get("Usuario");
		this.telefones = this.usuario.getTelefones();
	}

	public String atualizar() {
		this.usuarioRepository.atualizar(usuario);

		limparCampos();

		return "/index";
	}

	public void adicionarTelefone() {
		Telefone telefone = new Telefone();

		telefone.setDdd(Integer.parseInt(this.numeroTelefone.substring(1, 3)));
		telefone.setNumero(this.numeroTelefone.split(" ")[1]);
		telefone.setTipo(this.tipoTelefone);

		this.telefones.add(telefone);
		this.usuario.setTelefones(telefones);

		this.numeroTelefone = "";
		this.tipoTelefone = "";
	}

	private void limparCampos() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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
}
