package whiskill.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whiskill.dao.TrilhaDao;
import whiskill.model.Trilha;

@Controller
public class TrilhaController {

	@Inject 
	TrilhaDao trilhaDao;
	
	@RequestMapping( value = "/trilha/cadastro", method = RequestMethod.GET )
	public String trilhaCadastro(){
		return "TrilhaCadastro";
	}
	
	@RequestMapping( value = "/trilha/inserir", method = RequestMethod.POST )
	public String trilhaInserir( Trilha trilha ){
		trilhaDao.inserirTrilha( trilha );
		return "redirect:/trilha/listar";
	}
	
	@RequestMapping( value = "/trilha/listar", method = RequestMethod.GET )
	public String trilhaListar( Model model ){
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		return "TrilhaListar";
	}
	
	@RequestMapping( value = "/trilha/excluir", method = RequestMethod.GET )
	public String trilhaExcluir( @RequestParam int idTrilha ){
		trilhaDao.excluirTrilha( idTrilha );
		return "redirect:/trilha/listar";
	}
}
