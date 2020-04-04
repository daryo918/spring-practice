package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Cliente;

public interface IClienteService {
	
	public void save(Cliente cliente);
	public List<Cliente> findAll();
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	

}
