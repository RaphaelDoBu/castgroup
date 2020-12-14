package com.castgroupteste.banco.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.model.Conta;
import com.castgroupteste.banco.model.Transacao;
import com.castgroupteste.banco.repository.ClienteRepository;
import com.castgroupteste.banco.repository.ContaRepository;
import com.castgroupteste.banco.repository.TransacaoRepository;
import com.castgroupteste.banco.service.ContaService;
import com.castgroupteste.banco.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TransacaoRepository transacaoRepository;
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private ContaService contaService;

	@PreAuthorize("hasRole('USER')")
	@Transactional
	public void salvarTransacao(Conta contaCliente, float valorDeposito, float saldoAnterior, float saldoAtual) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		Transacao transacao = new Transacao();
		transacao.setConta(contaCliente);
		transacao.setData(dateFormat.format(date));
		transacao.setValorRetirada(valorDeposito);
		transacao.setSaldoAnterior(saldoAnterior);
		transacao.setSaldoAtual(saldoAtual);
		
		contaCliente.setSaldoAtual(saldoAtual);
		contaCliente.getTransacao().add(transacao);
		
		contaRepository.save(contaCliente);
		transacaoRepository.save(transacao);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public List<Transacao> extratoTransacoesContaCliente(Long idCliente, Long idConta){
		Cliente cliente = clienteRepository.findById(idCliente).get();
		Conta contaRecuperada = contaService.getConta(idConta).get();
		List<Transacao> transacoesConta = new ArrayList<>();
		
		for(Conta conta: cliente.getConta()) {
			if(conta.getNumConta() == contaRecuperada.getNumConta()) {
				transacoesConta = conta.getTransacao();
			}
		}
		
		return transacoesConta;
	}
	
}
