package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senhaConfirma", "field.required");

		Usuario usuario = (Usuario) target;

		if (!(usuario.getSenha().equals(usuario.getSenhaConfirma()))) {
			errors.rejectValue("senha", "notmatch.senha");
		}

		if (usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "shortpassword.senha");
		}

	}

}
