package com.castgroupteste.banco.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conta {
	
	@Id
    private int numConta;
    @Column
    private float saldoAtual;
    @Column
    private String tipoConta;
    @Column
    private int agencia;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;
    
    public Conta() {
		super();
	}
   
	public Conta(int numConta, float saldoAtual, int agencia, String tipoConta, List<Transacao> transacao,
			Cliente cliente) {
		super();
		this.numConta = numConta;
		this.saldoAtual = saldoAtual;
		this.agencia = agencia;
		this.tipoConta = tipoConta;
		this.transacao = transacao;
		this.cliente = cliente;
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

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public List<Transacao> getTransacao() {
		return transacao;
	}

	public void setTransacao(List<Transacao> transacao) {
		this.transacao = transacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
    
}
