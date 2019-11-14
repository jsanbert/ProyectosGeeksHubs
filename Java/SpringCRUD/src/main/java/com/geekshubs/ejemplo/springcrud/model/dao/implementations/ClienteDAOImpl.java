package com.geekshubs.ejemplo.springcrud.model.dao.implementations;

import com.geekshubs.ejemplo.springcrud.model.dao.interfaces.IClienteDAO;
import com.geekshubs.ejemplo.springcrud.model.entities.Cliente;
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
    public Cliente findClientById(Long id) {
        return em.find(Cliente.class, id);
    }

    // ---------------------------------------------
    @Override
    @Transactional
    public void insertOrUpdateClient(Cliente c) {
        if(c.getId() != null && c.getId() > 0)
            em.merge(c);
        else
            em.persist(c);
    }

    @Override
    @Transactional
    public void removeClientById(Long id) {
        em.remove(this.findClientById(id));
    }

    // ---------------------------------------------

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllClients() {
        return em.createQuery("SELECT c FROM Cliente c").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllClientsWithName(String nombre) {
        return em.createQuery("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre,'%'))").setParameter("nombre", nombre).getResultList();
    }
}