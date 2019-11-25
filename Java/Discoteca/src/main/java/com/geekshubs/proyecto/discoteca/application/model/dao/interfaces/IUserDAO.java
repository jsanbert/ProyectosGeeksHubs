package com.geekshubs.proyecto.discoteca.application.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.application.model.entities.User;

import java.util.List;

public interface IUserDAO {
    // ============================= CREATE =============================

    void insertUser(User e);





    // ============================== READ ==============================

    User findUserById(Long id);
    List<User> findAll();
    List<User> findUsersWithName(String name);
    Boolean userExists(String username);
    User getLoggedUser(String username, String password);
    List<User> findUsersRegisteredToAnEvent(Long eventId);





    // ============================== UPDATE ==============================

    void updateUser(User updatedUser);






    // ============================== DELETE ==============================

    void deleteUserById(Long id);

}
