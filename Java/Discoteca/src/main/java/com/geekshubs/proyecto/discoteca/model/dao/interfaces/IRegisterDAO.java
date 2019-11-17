package com.geekshubs.proyecto.discoteca.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.model.entities.Register;

import java.util.List;

public interface IRegisterDAO {
    // ============================= CREATE =============================

    void insertRegister(Register e);





    // ============================== READ ==============================

    Register findRegisterById(Long id);
    List<Register> findAll();
    List<Register> findRegistersWithName(String name);






    // ============================== UPDATE ==============================

    void updateRegister(Register updatedRegister);






    // ============================== DELETE ==============================

    void deleteRegisterById(Long id);

}
