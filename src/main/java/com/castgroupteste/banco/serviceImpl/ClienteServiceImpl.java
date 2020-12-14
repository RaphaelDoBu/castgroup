package com.castgroupteste.banco.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.castgroupteste.banco.dto.DadosClienteDTO;
import com.castgroupteste.banco.model.Cliente;
import com.castgroupteste.banco.model.Endereco;
import com.castgroupteste.banco.repository.ClienteRepository;
import com.castgroupteste.banco.repository.EnderecoRepository;
import com.castgroupteste.banco.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@PreAuthorize("hasRole('ADMIN')")
	public Cliente cadastrarCliente(DadosClienteDTO dadosClienteDTO) {
		Endereco enderecoCliente = new Endereco();
		enderecoCliente.setCidade(dadosClienteDTO.getEnderecoDTO().getCidade());
		enderecoCliente.setEstado(dadosClienteDTO.getEnderecoDTO().getEstado());
		enderecoCliente.setNumero(dadosClienteDTO.getEnderecoDTO().getNumero());
		enderecoCliente.setPais(dadosClienteDTO.getEnderecoDTO().getPais());
		enderecoCliente.setRua(dadosClienteDTO.getEnderecoDTO().getRua());

		Cliente clienteNovo = new Cliente();
		clienteNovo.setCpf(dadosClienteDTO.getCpf());
		clienteNovo.setEmail(dadosClienteDTO.getEmail());
		clienteNovo.setIdade(dadosClienteDTO.getIdade());
		clienteNovo.setNome(dadosClienteDTO.getNome());
		clienteNovo.setTelefone(dadosClienteDTO.getTelefone());
		clienteNovo.setDataNascimento(dadosClienteDTO.getDataNascimento());
		enderecoRepository.save(enderecoCliente);
		
		clienteNovo.setEndereco(enderecoCliente);
		clienteRepository.save(clienteNovo);
		return clienteNovo;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Cliente dadosCliente(Long idCliente) {
		Cliente cliente = clienteRepository.findById(idCliente).get();
		return cliente;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void deletarCliente(Long idCliente) {
		clienteRepository.deleteById(idCliente);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Optional<Cliente> getCliente(Long idCliente) {
		return clienteRepository.findById(idCliente);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Cliente atualizarCliente(Long idCliente, DadosClienteDTO dadosClienteDTO) {
		Cliente clienteCadastrado = getCliente(idCliente).get();
	
		Endereco novoEndereco = new Endereco();
		novoEndereco.setCidade(dadosClienteDTO.getEnderecoDTO().getCidade());
		novoEndereco.setEstado(dadosClienteDTO.getEnderecoDTO().getEstado());
		novoEndereco.setNumero(dadosClienteDTO.getEnderecoDTO().getNumero());
		novoEndereco.setPais(dadosClienteDTO.getEnderecoDTO().getPais());
		novoEndereco.setRua(dadosClienteDTO.getEnderecoDTO().getRua());

		clienteCadastrado.setEmail(dadosClienteDTO.getEmail());
		clienteCadastrado.setIdade(dadosClienteDTO.getIdade());
		clienteCadastrado.setNome(dadosClienteDTO.getNome());
		clienteCadastrado.setTelefone(dadosClienteDTO.getTelefone());
		clienteCadastrado.setEndereco(novoEndereco);
		enderecoRepository.save(novoEndereco);
		clienteRepository.save(clienteCadastrado);
		return clienteCadastrado;
	}
}
