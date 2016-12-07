package br.com.jkavdev.dominespring.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.jkavdev.dominespring.loja.daos.ProdutoDao;
import br.com.jkavdev.dominespring.loja.model.Produto;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoDao produtoDao;

	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}

	@RequestMapping(value = "/produtos/add", method = RequestMethod.POST)
	public String save(Produto produto) {
		System.out.println("Cadastro o produto");

		produtoDao.salva(produto);

		return "produtos/ok";
	}

}
