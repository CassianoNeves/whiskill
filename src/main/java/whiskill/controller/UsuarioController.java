package whiskill.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whiskill.dao.UsuarioDao;
import whiskill.model.Usuario;


@Controller
public class UsuarioController {
	
	@Inject 
	UsuarioDao usuarioDao;
//	Aqui vai ser a nossa home
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String index() {
			return "home";
	}
	
	@RequestMapping( value = "/usuario/semPermissao", method = RequestMethod.GET )
	public String usuarioSemPermissao( Model model, @RequestParam(defaultValue="false") boolean error ) {
		if( error ){
			model.addAttribute( "erro", "Usuário ou Senha inválidos." );
		}
		
		return "semPermissao";
	}
	
	@RequestMapping( value = "/usuario/login", method = RequestMethod.GET )
	public String login( Model model, @RequestParam(defaultValue="false") boolean error ) {
		if( error ){
			model.addAttribute( "erro", "Usuário ou Senha inválidos." );
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/usuario/validar", method = RequestMethod.POST)
	public String usuarioValidar( Model model, HttpSession session, Usuario usuarioLogado){
		Usuario usuario = usuarioDao.existeUsuario( usuarioLogado );

		if( usuario != null ){
			session.setAttribute( "usuarioLogado" , usuario );
			return "redirect:/";
		}
		
		return "redirect:/usuario/login?error=true";
	}
	
	@RequestMapping(value = "/usuario/sair", method = RequestMethod.GET)
	public String deslogarUsuario( HttpSession session ){
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/usuario/cadastro", method = RequestMethod.GET)
	public String usuarioCadastro( Usuario usuario ){
		return "UsuarioCadastro";
	}
	
	@RequestMapping(value = "/usuario/inserir", method = RequestMethod.POST)
	public String usuarioInserir(Model model, Usuario usuario ){
		usuarioDao.inserirUsuario( usuario );
		model.addAttribute( "ok", "Usuário insirido com sucesso" );
		return "redirect:/usuario/listar";
	}
	
	@RequestMapping(value = "/usuario/listar", method = RequestMethod.GET)
	public String usuarioListar(Model model ){
		model.addAttribute( "usuarios", usuarioDao.buscaTodosUsuarios() );
		return "UsuarioListar";
	}
	
	@RequestMapping(value = "/usuario/excluir", method = RequestMethod.GET)
	public String usuarioExcluir( @RequestParam int idUsuario ){
		usuarioDao.excluir( idUsuario );
		return "redirect:/usuario/listar";
	}
}