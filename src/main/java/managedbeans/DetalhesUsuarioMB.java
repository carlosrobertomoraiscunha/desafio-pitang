package managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import models.Usuario;
import repository.interfaces.IUsuarioRepository;

@Named(value = "detalhesUsuarioMB")
@SessionScoped
public class DetalhesUsuarioMB implements Serializable {

	private static final long serialVersionUID = 3086863545458062996L;

	@EJB
	private IUsuarioRepository usuarioRepository;

	private Usuario usuario;

	@PostConstruct
	public void configurar() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		this.usuario = (Usuario) sessionMap.get("Usuario");
	}

	public String deletarUsuario() {
		this.usuarioRepository.removerPorId(usuario.getId());

		return "/index";
	}

	public String logout() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		sessionMap.remove("Usuario");

		return "/index";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
