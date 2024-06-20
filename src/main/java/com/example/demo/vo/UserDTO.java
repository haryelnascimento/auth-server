package com.example.demo.vo;

public class UserDTO {

    private String cdUsuario;
    private String password;
    private String nmUsuario;
    private String nuCpfcnpj;
    private String flSuperUsuario;
    private String deEmail;
    private String nuTelefone;

    public String getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(String cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getNuCpfcnpj() {
        return nuCpfcnpj;
    }

    public void setNuCpfcnpj(String nuCpfcnpj) {
        this.nuCpfcnpj = nuCpfcnpj;
    }

    public String getFlSuperUsuario() {
        return flSuperUsuario;
    }

    public void setFlSuperUsuario(String flSuperUsuario) {
        this.flSuperUsuario = flSuperUsuario;
    }

    public String getDeEmail() {
        return deEmail;
    }

    public void setDeEmail(String deEmail) {
        this.deEmail = deEmail;
    }

	public String getNuTelefone() {
		return nuTelefone;
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}
}
