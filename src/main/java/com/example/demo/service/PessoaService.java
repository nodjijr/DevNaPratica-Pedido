package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PessoaDAO;
import com.example.demo.entities.Pessoa;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaDAO pessoaDAO;

	public List<Pessoa> buscarTodos() {
		return pessoaDAO.buscarTodos();
	}
	
	public Optional<Pessoa> buscar(Long pessoaId) {
		return pessoaDAO.buscar(pessoaId);
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		boolean temPessoaComMesmoEmail = pessoaDAO.buscarTodos()
			.stream()
			.anyMatch(outroPessoa -> outroPessoa.getEmail().equals(pessoa.getEmail()));
		
		if (temPessoaComMesmoEmail) {
			throw new IllegalArgumentException("Já existe um pessoa com este e-mail!");
		}
		
		return pessoaDAO.salvar(pessoa);
	}
	
	public Pessoa alterar(Pessoa pessoa) {
		return pessoaDAO.alterar(pessoa);
	}
	
	public void excluir(Long pessoaId) {
		pessoaDAO.excluir(pessoaId);
	}

	
}
