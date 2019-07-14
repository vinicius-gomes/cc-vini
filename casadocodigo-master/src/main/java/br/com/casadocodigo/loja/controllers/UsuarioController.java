package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Transactional
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;
	
	@Autowired
	private RoleDAO roleDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuarios/usuariosForm");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listarUsuarios() {
		List<Usuario> usuarios = dao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/usuariosLista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		}
		
			dao.gravar(usuario);

		redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping("/formRole")
	public ModelAndView formRole(String email) {
		ModelAndView modelAndView = new ModelAndView("usuarios/formRole");
		List<Role> roles = roleDao.listar();
		Usuario usuario = dao.loadUserByUsername(email);
		
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("roles", roles);
		return modelAndView;
		
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView usuarioExiste(RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("mail", "Usuário já existe!");
		return new ModelAndView("redirect:/usuarios/form");
	}
	
	@RequestMapping("/updateRole")
	public ModelAndView update(Usuario usuario, RedirectAttributes redirectAttributes) {
		dao.update(usuario);
		redirectAttributes.addFlashAttribute("role", "Role alterada!!!");
		return new ModelAndView("redirect:/usuarios");
	}
}
