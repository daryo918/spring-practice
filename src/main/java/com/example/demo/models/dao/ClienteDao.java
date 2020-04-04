package com.example.demo.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Cliente;


@Component
public class ClienteDao implements IClienteDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		if(cliente.getId()!=null) {
			em.merge(cliente);
		}
		else {
		em.persist(cliente);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		
		return em.find(Cliente.class, id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Cliente cliente=findOne(id);
		em.remove(cliente);
		
	}
	



}
