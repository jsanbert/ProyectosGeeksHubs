package com.geekshubs.proyecto.discoteca.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.model.entities.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IEventDAO {
    // ============================= CREATE =============================

    void insertEvent(Event e);





    // ============================== READ ==============================

    Event findEventById(Long id);
    List<Event> findAll();
    List<Event> findEventsWithName(String name);






    // ============================== UPDATE ==============================

    void updateEvent(Event updatedEvent);






    // ============================== DELETE ==============================

    void deleteEventById(Long id);

}
