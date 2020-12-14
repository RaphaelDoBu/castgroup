package com.castgroupteste.banco.service;

import java.util.List;

import com.castgroupteste.banco.model.Conta;
import com.castgroupteste.banco.model.Transacao;

public interface TransacaoService {
	public void salvarTransacao(Conta contaCliente, float valorDeposito, float saldoAnterior, float saldoAtual);
	public List<Transacao> extratoTransacoesContaCliente(Long idCliente, Long idConta);
}
