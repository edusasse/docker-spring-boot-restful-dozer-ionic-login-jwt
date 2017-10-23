package com.edusasse.app.dto.enums;

public enum TipoEndereco {
    RESIDENCIAL("RESIDENCIAL"), EMPRESARIAL("EMPRESARIAL"),TEMPORARIO("TEMPORARIO");
	
	private String value;
	
	TipoEndereco(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static TipoEndereco convert(String value){
		TipoEndereco result = null;
		
		switch (value) {
		case "RESIDENCIAL":
			result = TipoEndereco.RESIDENCIAL;
			break;
		case "EMPRESARIAL":
			result = TipoEndereco.EMPRESARIAL;
			break;
		case "TEMPORARIO":
			result = TipoEndereco.TEMPORARIO;
			break;
		default:
			result = null;
			break;
		};
		
		return result;
	}
	
	public static String convert(TipoEndereco value){
		return value.getValue();
	}
	
}
