package com.edusasse.app.dto.enums;

public enum SituacaoCliente {
    ATIVO('A'), INATIVO('I'), BLOQUEADO('B'), PROSPECCAO('P');
	
	private char value;
	
	SituacaoCliente(char value){
		this.value = value;
	}

	public char getValue() {
		return value;
	}
	
	public static SituacaoCliente convert(char value){
		SituacaoCliente result = null;
		
		switch (value) {
		case 'A':
			result = SituacaoCliente.ATIVO;
			break;
		case 'I':
			result = SituacaoCliente.INATIVO;
		case 'B':
			result = SituacaoCliente.BLOQUEADO;
		case 'P':
			result = SituacaoCliente.PROSPECCAO;
		default:
			break;
		};
		
		return result;
	}
	
	public static char convert(SituacaoCliente value){
		return value.getValue();
	}
	
}
