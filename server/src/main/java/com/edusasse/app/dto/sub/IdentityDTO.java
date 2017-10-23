package com.edusasse.app.dto.sub;

import com.edusasse.app.dto.enums.Sexo;

public class IdentityDTO {
    private String primeiroNome;
    private String segundoNome;
	private Sexo sexo;

    public IdentityDTO(String primeiroNome, String segundoNome, Sexo sexo) {
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.sexo = sexo;
    }

    public IdentityDTO() {
    }

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSegundoNome() {
		return segundoNome;
	}

	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
    
}