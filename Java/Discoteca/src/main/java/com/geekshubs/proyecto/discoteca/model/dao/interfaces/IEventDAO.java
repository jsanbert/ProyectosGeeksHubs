package com.geekshubs.proyecto.discoteca.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.model.entities.Event;

import java.util.List;

public interface IEventDAO {
    // ============================= CREATE =============================

    void insertEvent(Event e);

    // ==================================================================




    // ============================== READ ==============================

    Event findEventById(Long id);
    List<Event> findEventsWithName(String name);

    // ==================================================================






    // ============================== UPDATE ==============================

    void updateEventById(Long id);

    // ==================================================================






    // ============================== DELETE ==============================

    void deleteEventById(Long id);

    // ==================================================================

}
