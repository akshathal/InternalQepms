package com.qepms.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Basic login form with JSR-303 Annotations for validation
 * 
 */
public class LoginForm {

	@NotNull(message = "User Name field is mandatory.")
	@Size(min = 3, max = 50, message = "User Name field must be greater than 3 but less than 50 characters.")
	private String username;

	@NotNull(message = "Password field is mandatory.")
	@Size(min = 8, max = 50, message = "Password field must be greater than 8 but less than 50 characters.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}