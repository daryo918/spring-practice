package com.example.demo.models.dao;

import java.util.List;

import com.example.demo.models.entity.Cliente;

public interface IClienteDao {
	
	public void save(Cliente cliente);
	public List<Cliente> findAll();
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	

}