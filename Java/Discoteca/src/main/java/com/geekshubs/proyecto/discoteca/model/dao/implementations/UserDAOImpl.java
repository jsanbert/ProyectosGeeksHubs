package com.geekshubs.proyecto.discoteca.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IUserDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;
import com.geekshubs.proyecto.discoteca.model.entities.User;
import com.geekshubs.proyecto.discoteca.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em;

    // ============================= CREATE =============================

    @Override
    @Transactional
    public void insertUser(User u) {
        em.persist(u);
    }




    // ============================== READ ==============================

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersWithName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',:name,'%'))", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean userExists(String username) {
        // no existe -> resultado.isEmpty() = true -> return false
        return !(em.createQuery("SELECT u FROM User u WHERE TRIM(LOWER(username)) = TRIM(LOWER(:username))")
                .setParameter("username", username)
                .getResultList().isEmpty());
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkUserLogin(String username, String password) {
        // no existe -> resultado.isEmpty() = true -> return false
        return !(em.createQuery("SELECT u FROM User u WHERE TRIM(LOWER(username)) = TRIM(LOWER(:username)) AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList().isEmpty());
    }



    // ============================== UPDATE ============================


    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        em.merge(updatedUser);
    }




    // ============================== DELETE ============================

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        
    }
}
