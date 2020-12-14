package com.castgroupteste.banco.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.castgroupteste.banco.dto.DadosClienteDTO;
import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value = "Retornar lista de todos os clientes salvos")
	@GetMapping(value="/admin/clientes")
	public ResponseEntity<List<Cliente>> clientes() {
		List<Cliente> clientes = clienteService.listaClientes();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	@ApiOperation(value = "Cadastrar novo cliente")
	@PostMapping(value="/admin/cliente")
	public ResponseEntity<Cliente> cadastrarCliente(@ModelAttribute("dadosCliente") DadosClienteDTO dadosClienteDTO) {
		Cliente clienteCadastrado = clienteService.cadastrarCliente(dadosClienteDTO);		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
	}
	
	@ApiOperation(value = "Retornar dados de um cliente específico")
	@GetMapping(value="/user/cliente/{idCliente}")
	public ResponseEntity<Cliente> cliente(@PathVariable("idCliente") Long idCliente) {
		Cliente cliente = clienteService.dadosCliente(idCliente);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@ApiOperation(value = "Deletar dados de um cliente específico")
	@RequestMapping(value="/admin/cliente/{idCliente}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deletarCliente(@PathVariable("idCliente") Long idCliente) {
		Cliente cliente = new Cliente();
		clienteService.deletarCliente(idCliente);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@ApiOperation(value = "Atualizar dados de um cliente específico")
	@PutMapping(value="/user/cliente/{idCliente}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable("idCliente") Long idCliente, @ModelAttribute("dadosCliente") DadosClienteDTO dadosClienteDTO) {
		Cliente clienteAlterado = clienteService.atualizarCliente(idCliente, dadosClienteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(clienteAlterado);
	}

}