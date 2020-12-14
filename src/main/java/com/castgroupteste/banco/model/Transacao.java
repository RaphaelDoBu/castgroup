package com.castgroupteste.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String data;
    @Column
    private float valorRetirada;
    @Column
    private float saldoAnterior;
    @Column
    private float saldoAtual;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaId", nullable = false)
    private Conta conta;
    
	public Transacao() {
		super();
	}

	public Transacao(Long id, String data, float valorRetirada, float saldoAnterior, float saldoAtual, Conta conta) {
		super();
		this.id = id;
		this.data = data;
		this.valorRetirada = valorRetirada;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
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

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	
}
