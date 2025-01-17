package com.geekshubs.proyecto.discoteca.application.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.application.model.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

@Repository
public class EventDAOImpl implements IEventDAO {

    @Autowired
    @Lazy
    private IRegisterDAO registerDAO;

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
    public Event findEventById(Long id) {
        return em.find(Event.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return em.createQuery("SELECT e FROM Event e", Event.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findEventsWithName(String overview) {
        return em.createQuery("SELECT e FROM Event e WHERE LOWER(e.overview) LIKE LOWER(CONCAT('%',:overview,'%'))", Event.class)
                .setParameter("overview", overview)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findEventsStartingFrom(Date date) {
        return em.createQuery("SELECT e FROM Event e WHERE e.date > :date", Event.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCapacityByEventId(Long id) {
        return this.findEventById(id).getCapacity();
    }




    // ============================== UPDATE ============================

    @Override
    @Transactional
    public void updateEvent(Event updatedEvent) {
        em.merge(updatedEvent);
    }






    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteEventById(Long id) {
        // primero borro registros asociados al evento
        registerDAO.findRegistersToAnEvent(id)
                .stream()
                .forEach(r -> registerDAO.deleteRegisterById(r.getId()));

        // después borro el evento en sí
        em.remove(this.findEventById(id));
    }
}
