package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.models.dao.IClienteDao;
import com.example.demo.models.entity.Cliente;
import com.example.demo.models.service.IClienteService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	
	@RequestMapping(value = "/listar1",method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo","lista de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	@RequestMapping(value = "/crear",method = RequestMethod.GET)
	public String crear(Model model) {
		
		model.addAttribute("titulo","formulario");
		model.addAttribute("cliente",new Cliente());
		return "crear";
	}
	
	
	@RequestMapping(value="/crear/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Cliente editado=null;
		
		if(id>0) {
			editado=clienteService.findOne(id);
		}
		else {
			return "redirect:/listar";
		}
		
		model.addAttribute("titulo","formulario");
		model.addAttribute("cliente",editado);
	
		return "crear";
	}
	
	@RequestMapping(value = "/crear",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","formulario error");
			return "crear";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:/listar1";
	}
	
@RequestMapping(value="/eliminar/{id}", method= RequestMethod.GET)
public String eliminar(@PathVariable Long id, Model model) {
	
	
	if(id>0) {
		
		clienteService.delete(id);

	}

	return "redirect:/listar1";
}

}
