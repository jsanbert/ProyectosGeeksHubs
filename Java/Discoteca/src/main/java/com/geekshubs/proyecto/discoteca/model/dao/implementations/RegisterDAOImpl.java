package com.geekshubs.proyecto.discoteca.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import com.geekshubs.proyecto.discoteca.model.entities.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RegisterDAOImpl implements IRegisterDAO {

    @Autowired
    private IEventDAO eventDAO;

    @PersistenceContext
    private EntityManager em;

    // ============================= CREATE =============================

    @Override
    @Transactional
    public void insertRegister(Register r) {
        Event e = eventDAO.findEventById(r.getEventId());
        e.setCapacity(e.getCapacity() - 1);
        eventDAO.insertEvent(e);
        em.persist(r);
    }




    // ============================== READ ==============================

    @Override
    @Transactional(readOnly = true)
    public Register findRegisterById(Long id) {
        return em.find(Register.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Register findRegisterByToken(String token) {
        List<Register> registers = em.createQuery("SELECT r FROM Register r WHERE r.token = :token")
                .setParameter("token", token)
                .getResultList();

        if(registers.isEmpty())
            return null;
        else
            return registers.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findAll() {
        return em.createQuery("SELECT r FROM Register r", Register.class)
                .getResultList();
    }



    // ============================== UPDATE ============================







    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteRegisterById(Long id) {
        Register r = this.findRegisterById(id);
        Event e = eventDAO.findEventById(r.getEventId());
        e.setCapacity(e.getCapacity() + 1);
        eventDAO.insertEvent(e);
        em.remove(this.findRegisterById(id));
    }
}
