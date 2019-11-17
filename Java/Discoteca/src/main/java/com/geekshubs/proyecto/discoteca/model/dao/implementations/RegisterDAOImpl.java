package com.geekshubs.proyecto.discoteca.model.dao.implementations;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RegisterDAOImpl implements IRegisterDAO {

    @PersistenceContext
    private EntityManager em;

    // ============================= CREATE =============================

    @Override
    @Transactional
    public void insertEvent(Register e) {
        em.persist(e);
    }





    // ============================== READ ==============================

    @Override
    @Transactional(readOnly = true)
    public Register findEventById(Long id) {
        return em.find(Register.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findAll() {
        return em.createQuery("SELECT e FROM Register e", Register.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findEventsWithName(String overview) {
        return em.createQuery("SELECT e FROM Register e WHERE LOWER(e.overview) LIKE LOWER(CONCAT('%',:overview,'%'))", Register.class)
                .setParameter("overview", overview)
                .getResultList();
    }




    // ============================== UPDATE ============================

    @Override
    @Transactional
    public void updateEvent(Register eventWithUpdatedFields) {
        em.merge(eventWithUpdatedFields);
    }






    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteEventById(Long id) {
        em.remove(this.findEventById(id));
    }
}
