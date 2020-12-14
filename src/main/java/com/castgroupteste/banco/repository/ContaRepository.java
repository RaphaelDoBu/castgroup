package com.castgroupteste.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castgroupteste.banco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
