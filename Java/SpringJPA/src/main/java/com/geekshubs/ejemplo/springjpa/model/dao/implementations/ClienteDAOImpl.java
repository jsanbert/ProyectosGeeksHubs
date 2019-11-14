package com.geekshubs.ejemplo.springjpa.model.dao.implementations;

import com.geekshubs.ejemplo.springjpa.model.dao.interfaces.IClienteDAO;
import com.geekshubs.ejemplo.springjpa.model.entities.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDAOImpl implements IClienteDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(String id) {
        return (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id").setParameter("id", Long.parseLong(id)).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return em.createQuery("SELECT c FROM Cliente c").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllClientsWithName(String nombre) {
        return em.createQuery("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre,'%'))").setParameter("nombre", nombre).getResultList();
    }
}