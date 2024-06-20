package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthenticatedUser extends User {

    private String email;
    private String fullName;
    private String cpfCnpj;
    private String tpLogin;
    private String nuTelefone;
    private String sessionId;

    public AuthenticatedUser(
            String id,
            String password,
            String email,
            String fullName,
            String cpfCnpj,
            String tpLogin,
            String nuTelefone,
            String sessionId,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(id, password, authorities);
        this.email = email;
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.tpLogin = tpLogin;
        this.nuTelefone = nuTelefone;
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

	public String getTpLogin() {
		return tpLogin;
	}

	public void setTpLogin(String tpLogin) {
		this.tpLogin = tpLogin;
	}

	public String getNuTelefone() {
		return nuTelefone;
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
