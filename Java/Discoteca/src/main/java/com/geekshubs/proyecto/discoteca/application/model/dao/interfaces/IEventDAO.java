package com.geekshubs.proyecto.discoteca.application.model.dao.interfaces;

import com.geekshubs.proyecto.discoteca.application.model.entities.Event;

import java.util.Date;
import java.util.List;

public interface IEventDAO {
    // ============================= CREATE =============================

    void insertEvent(Event e);





    // ============================== READ ==============================

    Event findEventById(Long id);
    List<Event> findAll();
    List<Event> findEventsWithName(String name);
    List<Event> findEventsStartingFrom(Date date);
    Long getCapacityByEventId(Long id);






    // ============================== UPDATE ==============================

    void updateEvent(Event updatedEvent);






    // ============================== DELETE ==============================

    void deleteEventById(Long id);

}
