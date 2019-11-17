package com.geekshubs.proyecto.discoteca.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Register;
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
    public void insertRegister(Register e) {
        em.persist(e);
    }





    // ============================== READ ==============================

    @Override
    @Transactional(readOnly = true)
    public Register findRegisterById(Long id) {
        return em.find(Register.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findAll() {
        return em.createQuery("SELECT r FROM Register r", Register.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Register> findRegistersWithName(String overview) {
        return null;
    }




    // ============================== UPDATE ============================

    @Override
    @Transactional
    public void updateRegister(Register updatedRegister) {
        em.merge(updatedRegister);
    }






    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteRegisterById(Long id) {
        em.remove(this.findRegisterById(id));
    }
}
