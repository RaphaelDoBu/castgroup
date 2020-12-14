package com.castgroupteste.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castgroupteste.banco.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
