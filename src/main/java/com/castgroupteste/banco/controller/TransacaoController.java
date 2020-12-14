package com.castgroupteste.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.castgroupteste.banco.model.Conta;
import com.castgroupteste.banco.model.Transacao;
import com.castgroupteste.banco.service.ContaService;
import com.castgroupteste.banco.service.TransacaoService;

import io.swagger.annotations.ApiOperation;

@Controller
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	@Autowired
	private ContaService contaService;
	
	@ApiOperation(value = "Retorna extrato de transações realizadas pelo cliente por conta")
	@GetMapping(value="/user/cliente/{idCliente}/extratoTransacoes/conta/{idConta}")
	public ResponseEntity<List<Transacao>> extratoTransacoesContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta) {		
		List<Transacao> transacoes = transacaoService.extratoTransacoesContaCliente(idCliente, idConta);
		return ResponseEntity.status(HttpStatus.OK).body(transacoes);
	}
	
	@ApiOperation(value = "Cliente realiza saque na conta, enviando como parâmetro número da conta e o valor do saque")
	@PostMapping(value="/user/cliente/{idCliente}/conta/transacao/saque/{idConta}")
	public ResponseEntity<Conta> sacarDinheiroContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta, @RequestParam("valorSaque") float valorSaque) {		
		Conta contaCliente = contaService.sacarDinheiro(idCliente, idConta, valorSaque);
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCliente);
	}
	
	@ApiOperation(value = "Cliente realiza deposito na conta, enviando como parâmetro número da conta e o valor do saque")
	@PostMapping(value="/user/cliente/{idCliente}/conta/transacao/deposito/{idConta}")
	public ResponseEntity<Conta> depositarDinheiroContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta, @RequestParam("valorDeposito") float valorDeposito) {	
		Conta contaCliente = contaService.depositarDinheiro(idCliente, idConta, valorDeposito);
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCliente);
	}
}
