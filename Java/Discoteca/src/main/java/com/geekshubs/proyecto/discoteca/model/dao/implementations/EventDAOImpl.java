package com.geekshubs.proyecto.discoteca.model.dao.implementations;

import com.geekshubs.proyecto.discoteca.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.model.entities.Event;

import java.util.List;

public class EventDAOImpl implements IEventDAO {
    // ============================= CREATE =============================

    public void insertEvent(Event e) { }

    // ==================================================================




    // ============================== READ ==============================

    public Event findEventById(Long id) { return null; }
    public List<Event> findEventsWithName(String name) { return null; }

    // ==================================================================






    // ============================== UPDATE ============================

    public void updateEventById(Long id) { }

    // ==================================================================






    // ============================== DELETE ============================

    public void deleteEventById(Long id) { }

    // ==================================================================
}
