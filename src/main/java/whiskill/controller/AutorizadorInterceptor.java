package whiskill.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import whiskill.model.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	  public boolean preHandle(HttpServletRequest request,
			  HttpServletResponse response,
			  Object controller) throws Exception {
	    
			String uri = request.getRequestURI();
			
			if( uri.endsWith("/usuario/login") || 
					uri.endsWith("/usuario/semPermissao") ||
					uri.endsWith("/usuario/validar") ||
					uri.endsWith("scripts") ||
					//uri.endsWith("/") ||
					uri.contains("resources") ){
				
				return true;
			}
	      
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		
			if(usuario != null) {
		        return true;
		    }
			
		    response.sendRedirect("/usuario/login");
		    return false;  
	  }
}