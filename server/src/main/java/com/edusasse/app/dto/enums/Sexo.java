package com.edusasse.app.dto.enums;

public enum Sexo {
    MASCULINO('M'), FEMININO('F');
	
	private char value;
	
	Sexo(char value){
		this.value = value;
	}

	public char getValue() {
		return value;
	}
	
	public static Sexo convert(char value){
		Sexo result = null;
		
		switch (value) {
		case 'M':
			result = MASCULINO;
			break;
		case 'F':
			result = FEMININO;
		default:
			break;
		};
		
		return result;
	}
	
	public static char convert(Sexo value){
		return value.getValue();
	}
	
}
