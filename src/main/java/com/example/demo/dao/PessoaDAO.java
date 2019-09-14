package com.example.demo.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.entities.Pessoa;

public class PessoaDAO {

	private static AtomicLong idSequence = new AtomicLong(0L);
	private HashMap<Long, Pessoa> pessoasRegistrados = new LinkedHashMap<>();

	public List<Pessoa> buscarTodos() {
		return new LinkedList<Pessoa>(pessoasRegistrados.values());
	}

	public Optional<Pessoa> buscar(Long pessoaId) {
		return Optional.ofNullable(pessoasRegistrados.get(pessoaId));
	}

	public Pessoa salvar(Pessoa pessoa) {
		pessoa.setId(idSequence.getAndIncrement());
		pessoasRegistrados.put(pessoa.getId(), pessoa);
		return pessoa;
	}

	public Pessoa alterar(Pessoa pessoa) {
		pessoasRegistrados.put(pessoa.getId(), pessoa);
		return pessoa;
	}

	public void excluir(Long pessoaId) {
		pessoasRegistrados.remove(pessoaId);
	}

}
