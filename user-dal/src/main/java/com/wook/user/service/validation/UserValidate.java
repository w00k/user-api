package com.wook.user.service.validation;

import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import com.wook.user.exception.MessageException;
import com.wook.user.model.dto.UserDto;

public class UserValidate {

	@Autowired
	private MessageSource messageSource;

	public void validateUser(UserDto user) throws MessageException {
		
		if (user.getName() == null || user.getName().isBlank()) {
			throw new MessageException(messageSource.getMessage("user.validation.noname", null, Locale.US));
		}

		if (user.getPassword() == null || user.getPassword().isBlank()) {
			throw new MessageException(messageSource.getMessage("user.validation.nopassword", null, Locale.US));
		}

		if (user.getEmail() == null || user.getEmail().isBlank()) {
			throw new MessageException(messageSource.getMessage("user.validation.noemail", null, Locale.US));
		}

		if (!validEmail(user.getEmail())) {
			throw new MessageException(messageSource.getMessage("user.validation.emailbadformat", null, Locale.US));
		}

		if (!validPassword(user.getPassword())) {
			throw new MessageException(messageSource.getMessage("user.validation.passwordbadformat", null, Locale.US));
		}

	}

	// correo expresion regular aaaaa@dominio.cl
	public Boolean validEmail(String email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	// clave expresion regular 1 Mayuscula y 2 numeros
	public Boolean validPassword(String password) {
		Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d){2,}[A-Za-z\\d]{8,}$");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public String getToken() {
		return UUID.randomUUID().toString();
	}

	public UserValidate() {
		super();
	}
	
}
