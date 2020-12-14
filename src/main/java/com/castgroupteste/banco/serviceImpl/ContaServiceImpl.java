package com.castgroupteste.banco.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castgroupteste.banco.dto.ContaDTO;
import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.model.Conta;
import com.castgroupteste.banco.repository.ClienteRepository;
import com.castgroupteste.banco.repository.ContaRepository;
import com.castgroupteste.banco.service.ContaService;
import com.castgroupteste.banco.service.TransacaoService;

@Service
public class ContaServiceImpl implements ContaService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private TransacaoService transacaoService;

	/**
	 * Gera número randomico de conta com exatamente 8 dígitos
	 * @return Objeto inicial Conta com atributo numConta setado com conta.
	 */
	@PreAuthorize("hasRole('ADMIN')")
	public Conta gerarNumeroConta() {
		Conta conta = new Conta();
		
		int numero = (int)(10000000 + Math.random() * (100000000 - 10000000 + 1));
		conta.setNumConta(numero);
		return conta;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public Cliente cadastrarContaCliente(Long idCliente, ContaDTO contaDTO) {
		Conta conta = gerarNumeroConta();
		conta.setAgencia(contaDTO.getNumAgencia());
		conta.setTipoConta(contaDTO.getTipoConta());
		
		Cliente cliente = clienteRepository.findById(idCliente).get();
		cliente.getConta().add(conta);
		conta.setCliente(cliente);
		
		contaRepository.save(conta);
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Cliente alterarContaCliente(Long idCliente, Long idConta, ContaDTO contaDTO) {
		Conta conta = contaRepository.findById(idConta).get();
		conta.setAgencia(contaDTO.getNumAgencia());
		conta.setTipoConta(contaDTO.getTipoConta());
		
		Cliente cliente = clienteRepository.findById(idCliente).get();
		cliente.getConta().add(conta);
		conta.setCliente(cliente);
		
		contaRepository.save(conta);
		clienteRepository.save(cliente);
		
		return null;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Conta dadosContaCliente(Long idCliente, Long idConta) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		Conta contaRecuperada = new Conta();
		for(Conta conta: cliente.getConta()) {
			if(conta.getNumConta() == idConta) {
				contaRecuperada = conta;
			}
		}
		
		return contaRecuperada;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public Conta removerContaCliente(Long idCliente, Long idConta) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		Conta contaRecuperada = getConta(idConta).get();
		
		cliente.getConta().remove(contaRecuperada);
		contaRepository.delete(contaRecuperada);
		
		return contaRecuperada;
	}
	
	public Optional<Conta> getConta(Long idConta) {
		return contaRepository.findById(idConta);
	}

	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Conta sacarDinheiro(Long idCliente, Long idConta, float valorSaque) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		Conta contaRecuperada = getConta(idConta).get();
		
		float saldoAnterior = contaRecuperada.getSaldoAtual();
		
		for(Conta conta: cliente.getConta()) {
			if(conta.getNumConta() == contaRecuperada.getNumConta() && saldoAnterior > 0 && saldoAnterior >= valorSaque) {
				float saldoAtual = saldoAnterior - valorSaque;
				transacaoService.salvarTransacao(contaRecuperada, valorSaque, saldoAnterior, saldoAtual);
			}
		}
		
		return contaRecuperada;
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public Conta depositarDinheiro(Long idCliente, Long idConta, float valorDeposito) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		Conta contaRecuperada = getConta(idConta).get();
		
		for(Conta conta: cliente.getConta()) {
			if(conta.getNumConta() == contaRecuperada.getNumConta() && valorDeposito > 0) {
				float saldoAnterior = contaRecuperada.getSaldoAtual();
				float saldoAtual = saldoAnterior + valorDeposito;
				
				transacaoService.salvarTransacao(contaRecuperada, valorDeposito, saldoAnterior, saldoAtual);
			}
		}
		
		return contaRecuperada;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public float consultarSaldo(Long idCliente, Long idConta) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		Conta contaRecuperada = getConta(idConta).get();
		float saldo = 0;
		
		for(Conta conta: cliente.getConta()) {
			if(conta.getNumConta() == contaRecuperada.getNumConta()) {
				saldo = conta.getSaldoAtual();
			}
		}
		
		return saldo;
	}
}
