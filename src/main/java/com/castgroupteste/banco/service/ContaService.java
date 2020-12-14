package com.castgroupteste.banco.service;

import java.util.Optional;

import com.castgroupteste.banco.dto.ContaDTO;
import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.model.Conta;

public interface ContaService {
	public Conta gerarNumeroConta();
	public Cliente cadastrarContaCliente(Long idCliente, ContaDTO contaDto);
	public Optional<Conta> getConta(Long idConta);
	public Cliente alterarContaCliente(Long idCliente, Long idConta, ContaDTO contaDto);
	public Conta removerContaCliente(Long idCliente, Long idConta);
	public Conta dadosContaCliente(Long idCliente, Long idConta);
	public Conta sacarDinheiro(Long idCliente, Long idConta, float valorSaque);
	public Conta depositarDinheiro(Long idCliente, Long idConta, float valorDeposito);
	public float consultarSaldo(Long idCliente, Long idConta);
}
