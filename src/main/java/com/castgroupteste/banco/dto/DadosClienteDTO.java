package com.castgroupteste.banco.dto;

import java.util.List;

public class DadosClienteDTO {
	private String cpf;
	private String nome;
    private String email;
    private int idade;
    private String telefone;
    private String dataNascimento;
    private EnderecoDTO enderecoDTO;
    private List<ContaDTO> contaDTO;
    
	public DadosClienteDTO() {
		super();

	}

	public DadosClienteDTO(String cpf, String nome, String email, int idade, String telefone, String dataNascimento,
			EnderecoDTO enderecoDTO, List<ContaDTO> contaDTO) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.enderecoDTO = enderecoDTO;
		this.contaDTO = contaDTO;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	public List<ContaDTO> getContaDTO() {
		return contaDTO;
	}

	public void setContaDTO(List<ContaDTO> contaDTO) {
		this.contaDTO = contaDTO;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
