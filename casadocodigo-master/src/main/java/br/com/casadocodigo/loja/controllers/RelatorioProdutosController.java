package br.com.casadocodigo.loja.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Produto> relatorioProdutos(@RequestParam(required = false) Calendar data) {
		return produtoDao.relatorioData(data);
	}
}
