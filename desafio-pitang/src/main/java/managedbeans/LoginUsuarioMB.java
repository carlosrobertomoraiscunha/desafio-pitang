package managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import models.Usuario;
import models.dto.LoginUsuarioDTO;
import repository.interfaces.IUsuarioRepository;

@Named(value = "loginUsuarioMB")
@SessionScoped
public class LoginUsuarioMB implements Serializable {

	private static final long serialVersionUID = 3805198442864567118L;

	@EJB
	private IUsuarioRepository usuarioRepository;

	private LoginUsuarioDTO loginUsuarioDTO;

	@PostConstruct
	public void configurar() {
		this.loginUsuarioDTO = new LoginUsuarioDTO();
	}

	public String logar() {
		Usuario usuario = this.usuarioRepository.pegarPorEmailESenha(loginUsuarioDTO.getEmail(),
				loginUsuarioDTO.getSenha());

		if (usuario == null) {
			alertarUsuario(FacesMessage.SEVERITY_ERROR, "As credeenciais estão incorretas!");

			return null;
		}

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();

		sessionMap.put("Usuario", usuario);

		limparCampos();

		return "/detalhes";
	}

	private void limparCampos() {
		this.loginUsuarioDTO.setEmail("");
		this.loginUsuarioDTO.setSenha("");
	}

	private void alertarUsuario(Severity gravidade, String msg) {
		FacesMessage mensagem = new FacesMessage(gravidade, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public LoginUsuarioDTO getLoginUsuarioDTO() {
		return loginUsuarioDTO;
	}

	public void setLoginUsuarioDTO(LoginUsuarioDTO loginUsuarioDTO) {
		this.loginUsuarioDTO = loginUsuarioDTO;
	}
}
