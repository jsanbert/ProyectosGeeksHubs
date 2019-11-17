package com.geekshubs.proyecto.discoteca.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.model.entities.Register;

import java.util.List;

public interface IRegisterDAO {
    // ============================= CREATE =============================

    void insertRegister(Register r);





    // ============================== READ ==============================

    Register findRegisterById(Long id);
    Register findRegisterByToken(String token);
    List<Register> findAll();






    // ============================== UPDATE ==============================







    // ============================== DELETE ==============================

    void deleteRegisterById(Long id);

}
