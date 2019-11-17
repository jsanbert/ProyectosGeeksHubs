package com.geekshubs.proyecto.discoteca.model.dao.interfaces;

import java.util.List;

public interface IRegisterDAO {
    // ============================= CREATE =============================

    void insertEvent(Register e);





    // ============================== READ ==============================

    Register findEventById(Long id);
    List<Register> findAll();
    List<Register> findEventsWithName(String name);






    // ============================== UPDATE ==============================

    void updateEvent(Register eventWithUpdatedFields);






    // ============================== DELETE ==============================

    void deleteEventById(Long id);

}
