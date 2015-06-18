package whiskill.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import whiskill.dao.ColaboradorDao;
import whiskill.dao.ProjetoColaboradorDao;
import whiskill.model.Colaborador;
import whiskill.model.ColaboradorData;

@Controller
public class ProjetoColaboradorController {
	
	@Inject 
	ProjetoColaboradorDao pcDao;
	
	@Inject
	ColaboradorDao colaboradorDao;
	
	@ResponseBody
	@RequestMapping( value = "/projeto/cadastro/colaborador", method = RequestMethod.POST )
	public ColaboradorData projetoCadastro( @RequestParam int idProjeto,@RequestParam int idColaborador,
			@RequestParam String dataInicio, @RequestParam String dataFim ){
		pcDao.inserirColaboradorEmProjeto(idProjeto, idColaborador, dataInicio, dataFim);
		return colaboradorDao.bucarColaboradorEDatasPorId(idColaborador);
	}
	
	
	

}
