package com.edusasse.app.dto.enums;

public enum TipoDocumento {
    CPF("CPF"), RG("RG"),PASSAPORTE("PASSAPORTE"), OUTRO("OUTRO");
	
	private String value;
	
	TipoDocumento(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static TipoDocumento convert(String value){
		TipoDocumento result = null;
		
		switch (value) {
		case "CPF":
			result = CPF;
			break;
		case "RG":
			result = RG;
			break;
		case "PASSAPORTE":
			result = PASSAPORTE;
			break;
		default:
			result = OUTRO;
			break;
		};
		
		return result;
	}
	
	public static String convert(TipoDocumento value){
		return value.getValue();
	}
	
}
