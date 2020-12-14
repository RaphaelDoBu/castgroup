package com.castgroupteste.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.castgroupteste.banco.dto.ContaDTO;
import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.model.Conta;
import com.castgroupteste.banco.service.ContaService;

import io.swagger.annotations.ApiOperation;

@Controller
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@ApiOperation(value = "Criar conta para um cliente específico")
	@PostMapping(value="/admin/cliente/{idCliente}/conta")
	public ResponseEntity<Cliente> criarContaCliente(@PathVariable("idCliente") Long idCliente, @ModelAttribute("dadosConta") ContaDTO dadosConta) {		
		Cliente cliente = contaService.cadastrarContaCliente(idCliente, dadosConta);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@ApiOperation(value = "Alterar dados de uma conta de um cliente específico")
	@PutMapping(value="/user/cliente/{idCliente}/conta/{idConta}")
	public ResponseEntity<Cliente> alterarContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta,
			@ModelAttribute("dadosConta") ContaDTO dadosConta) {		
		Cliente cliente = contaService.alterarContaCliente(idCliente, idConta, dadosConta);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@ApiOperation(value = "Retornar dados de uma conta de um cliente específico")
	@GetMapping(value="/user/cliente/{idCliente}/conta/{idConta}")
	public ResponseEntity<Conta> dadosContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta) {
		Conta conta = contaService.dadosContaCliente(idCliente, idConta);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	
	@ApiOperation(value = "Remover conta associada ao cliente específico")
	@DeleteMapping(value="/admin/cliente/{idCliente}/conta/{idConta}")
	public ResponseEntity<Conta> removerContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta) {
		Conta conta = contaService.removerContaCliente(idCliente, idConta);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	
	@ApiOperation(value = "Retorna o saldo do cliente por meio do número da conta")
	@GetMapping(value="/user/cliente/{idCliente}/conta/saldo/{idConta}")
	public ResponseEntity<Float> saldoContaCliente(@PathVariable("idCliente") Long idCliente, @PathVariable("idConta") Long idConta) {		
		float saldoAtual = contaService.consultarSaldo(idCliente, idConta);
		return ResponseEntity.status(HttpStatus.OK).body(saldoAtual);
	}

}
