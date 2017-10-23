package com.edusasse.app.dto.enums;

public enum SituacaoFinanceira {
    ADIMPLENTE('A'), INADIMPLENTE('I');
	
	private char value;
	
	SituacaoFinanceira(char value){
		this.value = value;
	}

	public char getValue() {
		return value;
	}
	
	public static SituacaoFinanceira convert(char value){
		SituacaoFinanceira result = null;
		
		switch (value) {
		case 'A':
			result = SituacaoFinanceira.ADIMPLENTE;
			break;
		case 'I':
			result = SituacaoFinanceira.INADIMPLENTE;
		default:
			break;
		};
		
		return result;
	}
	
	public static char convert(SituacaoFinanceira value){
		return value.getValue();
	}
	
}
