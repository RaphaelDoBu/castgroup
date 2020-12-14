package com.castgroupteste.banco.dto;

import java.util.Date;

public class TransacaoDTO {
	private Date data;
	private float valorRetirada;
	private float saldoAnterior;
    private float saldoAtual;
	private ContaDTO contaDTO;
    
    public TransacaoDTO() {
		super();
	}
    
	public TransacaoDTO(Date data, float valorRetirada, float saldoAnterior, float saldoAtual, ContaDTO contaDTO) {
		super();
		this.data = data;
		this.valorRetirada = valorRetirada;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.contaDTO = contaDTO;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValorRetirada() {
		return valorRetirada;
	}

	public void setValorRetirada(float valorRetirada) {
		this.valorRetirada = valorRetirada;
	}

	public float getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(float saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public float getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(float saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public ContaDTO getContaDTO() {
		return contaDTO;
	}

	public void setContaDTO(ContaDTO contaDTO) {
		this.contaDTO = contaDTO;
	}
    
    
}
