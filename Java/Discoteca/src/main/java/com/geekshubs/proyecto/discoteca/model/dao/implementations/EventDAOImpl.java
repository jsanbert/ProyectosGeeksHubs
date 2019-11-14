package com.geekshubs.proyecto.discoteca.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class EventDAOImpl implements IEventDAO {

    @PersistenceContext
    private EntityManager em;

    // ============================= CREATE =============================

    @Override
    @Transactional
    public void insertEvent(Event e) {
        em.persist(e);
    }





    // ============================== READ ==============================

    @Override
    @Transactional(readOnly = true)
    public Event findEventById(Long id) { return null; }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findEventsWithName(String name) { return null; }




    // ============================== UPDATE ============================

    @Override
    @Transactional
    public void updateEventById(Long id) { }






    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteEventById(Long id) { }

    // ==================================================================
}
