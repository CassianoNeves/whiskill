package whiskill.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whiskill.dao.ProjetoColaboradorDao;

@Controller
public class ProjetoColaboradorController {
	
	@Inject 
	ProjetoColaboradorDao PCDao;
	
	@ResponseBody
	@RequestMapping( value = "/projeto/cadastro/colaborador", method = RequestMethod.POST )
	public String projetoCadastro( int idProjeto, int idColaborador ){
		
		return "ok";
	}
	
	
	

}
