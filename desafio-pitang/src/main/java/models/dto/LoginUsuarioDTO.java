package models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginUsuarioDTO {

	@Email(message = "O email é inválido!")
	@NotBlank(message = "O email é obrigatório!")
	private String email;

	@NotBlank(message = "A senha é obrigatória!")
	private String senha;

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
}
