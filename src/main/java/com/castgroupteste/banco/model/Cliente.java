package com.castgroupteste.banco.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true)
    private String cpf;
    @Column
    private String nome;
    @Column(unique = true)
    private String email;
    @Column
    private int idade;
    @Column
    private String telefone;
    @Column
    private String dataNascimento;
    @OneToOne
    private Endereco endereco;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> conta = new ArrayList<>();
    
    public Cliente() {
    }
    
	public Cliente(String cpf, String nome, String email, int idade, String telefone, String dataNascimento,
			Endereco endereco, List<Conta> conta) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.conta = conta;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Conta> getConta() {
		return conta;
	}

	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}
    
	
    
}
