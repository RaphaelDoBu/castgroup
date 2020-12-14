package com.castgroupteste.banco.dto;

import java.util.List;

public class ContaDTO {
	
	private int numConta;
    private int numAgencia;
    private float saldoAtual;
    private String tipoConta;
    private List<TransacaoDTO> transacaoDTO;
    private DadosClienteDTO clienteDTO;
    
   	public ContaDTO() {
   		super();
   	}
   
	public ContaDTO(int numConta, float saldoAtual, String tipoConta, int numAgencia, List<TransacaoDTO> transacaoDTOs,
			DadosClienteDTO clienteDTO) {
		super();
		this.numConta = numConta;
		this.saldoAtual = saldoAtual;
		this.tipoConta = tipoConta;
		this.numAgencia = numAgencia;
		this.transacaoDTO = transacaoDTOs;
		this.clienteDTO = clienteDTO;
	}

	public int getNumConta() {
		return numConta;
	}
	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}
	public float getSaldoAtual() {
		return saldoAtual;
	}
	public void setSaldoAtual(float saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	
	public List<TransacaoDTO> getTransacaoDTOs() {
		return transacaoDTO;
	}
	public void setTransacaoDTOs(List<TransacaoDTO> transacaoDTOs) {
		this.transacaoDTO = transacaoDTOs;
	}
	public DadosClienteDTO getClienteDTO() {
		return clienteDTO;
	}
	public void setClienteDTO(DadosClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public int getNumAgencia() {
		return numAgencia;
	}

	public void setNumAgencia(int numAgencia) {
		this.numAgencia = numAgencia;
	}

}
