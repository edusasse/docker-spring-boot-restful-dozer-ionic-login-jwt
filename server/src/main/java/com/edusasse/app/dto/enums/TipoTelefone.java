package com.edusasse.app.dto.enums;

public enum TipoTelefone {
    FIXO("FIXO"), CELULAR("CELULAR"),EMPRESA("EMPRESA"), OUTRO("OUTRO");
	
	private String value;
	
	TipoTelefone(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static TipoTelefone convert(String value){
		TipoTelefone result = null;
		
		switch (value) {
		case "FIXO":
			result = TipoTelefone.FIXO;
			break;
		case "CELULAR":
			result = TipoTelefone.CELULAR;
			break;
		case "EMPRESA":
			result = TipoTelefone.EMPRESA;
			break;
		default:
			result = TipoTelefone.OUTRO;
			break;
		};
		
		return result;
	}
	
	public static String convert(TipoTelefone value){
		return value.getValue();
	}
	
}
