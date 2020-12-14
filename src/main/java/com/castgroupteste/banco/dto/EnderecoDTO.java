package com.castgroupteste.banco.dto;

public class EnderecoDTO {
	private String pais;
	private String estado;
	private String cidade;
	private String rua;
	private int numero;
	
	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(String pais, String estado, String cidade, String rua, int numero) {
		super();
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.rua = rua;
		this.numero = numero;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
