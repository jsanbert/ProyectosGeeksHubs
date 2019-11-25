package com.geekshubs.proyecto.discoteca.application.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.application.model.entities.Register;

import java.util.List;

public interface IRegisterDAO {
    // ============================= CREATE =============================

    void insertRegister(Register r);





    // ============================== READ ==============================

    Register findRegisterById(Long id);
    Register findRegisterByToken(String token);
    Boolean checkUserRegisteredToEvent(Long eventId, String username);
    List<Register> findAll();
    List<Register> findRegistersToAnEvent(Long eventId);
    Register findRegisterByUserAndEventId(Long userId, Long eventId);






    // ============================== UPDATE ==============================
    void updateRegister(Register updatedRegister);






    // ============================== DELETE ==============================

    void deleteRegisterById(Long id);

}
