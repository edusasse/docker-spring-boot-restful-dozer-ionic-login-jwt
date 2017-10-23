interface IIdentityDTO {
    primeiroNome: string;
	segundoNome: string;
	sexo: string;

}

interface IPessoaFisicaDTO {
    identity: IIdentityDTO;
	dataNascimento: Date;
}

interface IUsuarioDTO {
    pessoa: IPessoaFisicaDTO;
    apelido: string;
	senha: string;
	ativo: boolean;
	perfil: string;
}