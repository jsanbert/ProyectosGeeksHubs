package com.geekshubs.proyecto.discoteca.application.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.application.model.entities.Event;
import com.geekshubs.proyecto.discoteca.application.model.entities.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RegisterDAOImpl implements IRegisterDAO {

    @Autowired
    @Lazy // para resolver dependencia circular
    private IEventDAO eventDAO;

    @PersistenceContext
    private EntityManager em;

    // ============================= CREATE =============================

    @Override
    @Transactional
    public void insertRegister(Register r) {
        // Cojo evento asociado al registro que me llega
        Event e = eventDAO.findEventById(r.getEventId());

        // Actualizo evento restando 1 a la capacidad puesto que es registro nuevo
        e.setCapacity(e.getCapacity() - 1);
        eventDAO.insertEvent(e);

        // Guardo el registro
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
    public Boolean checkUserRegisteredToEvent(Long eventId, String username) {
        List result = em.createQuery("SELECT r FROM Register r INNER JOIN User u ON r.userId = u.id WHERE u.username = :username AND r.eventId = :eventId")
                .setParameter("username", username)
                .setParameter("eventId", eventId)
                .getResultList();

        return !result.isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findAll() {
        return em.createQuery("SELECT r FROM Register r", Register.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findRegistersToAnEvent(Long eventId) {
        return em.createQuery("SELECT r FROM Register r WHERE r.eventId = :eventId")
                .setParameter("eventId", eventId)
                .getResultList();
    }



    // ============================== UPDATE ============================







    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteRegisterById(Long id) {
        // Cojo registro
        Register r = this.findRegisterById(id);

        // Cojo evento asociado al registro
        Event e = eventDAO.findEventById(r.getEventId());

        // Aumento capacidad en 1 puesto que se borra 1 registro
        e.setCapacity(e.getCapacity() + 1);

        // Actualizo evento y borro registro
        eventDAO.updateEvent(e);
        em.remove(r);
    }
}
