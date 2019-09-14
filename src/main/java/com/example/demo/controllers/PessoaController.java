package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pessoa;
import com.example.demo.service.PessoaService;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	@RequestMapping()
	public ResponseEntity<List<Pessoa>> listar() {

		return ResponseEntity.ok(pessoaService.buscarTodos());
	}

	@RequestMapping(method = RequestMethod.POST, path = "/v1/pessoas")
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa ) {

		return new ResponseEntity<>(pessoaService.salvar(pessoa), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/v1/pessoas/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable int id) {
		Optional<Pessoa> pessoa = pessoaService.buscar(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/v1/pessoas/{cliente.id}")
	public Pessoa alterar(@RequestBody Pessoa pessoa) {
		return pessoaService.alterar(pessoa);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/v1/pessoas/{id}")
	public int excluir(@PathVariable int id) {
		return pessoaService.excluir(id);
	}
}
