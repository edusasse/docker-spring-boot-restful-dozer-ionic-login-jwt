package com.edusasse.app.dto.enums;

public enum ProfileAlignmentDTO {
    ALUNO('A'), PROFESSOR('P'), NUTRICIONISTA('N'), PSICOLOGA('S'), GESTOR('G');

	
	private char value;
	
	ProfileAlignmentDTO(char value){
		this.value = value;
	}

	public char getValue() {
		return value;
	}
	
	public static ProfileAlignmentDTO convert(char value){
		// TODO
		return null;
	}
	
	public static char convert(ProfileAlignmentDTO value){
		// TODO
		return ' ';
	}
	
}
