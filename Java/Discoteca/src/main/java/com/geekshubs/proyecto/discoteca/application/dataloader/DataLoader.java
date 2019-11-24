package com.geekshubs.proyecto.discoteca.application.dataloader;

import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IEventDAO;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IRegisterDAO;
import com.geekshubs.proyecto.discoteca.application.model.dao.interfaces.IUserDAO;
import com.geekshubs.proyecto.discoteca.application.model.entities.Event;
import com.geekshubs.proyecto.discoteca.application.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataLoader implements ApplicationRunner {

    private IEventDAO eventDAO;
    private IUserDAO userDAO;
    private IRegisterDAO registerDAO;

    @Autowired
    public DataLoader(IEventDAO eventDAO, IUserDAO userDAO, IRegisterDAO registerDAO) {
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
        this.registerDAO = registerDAO;
    }

    public void run(ApplicationArguments args) throws ParseException {
        User user = new User("jesus", "123", "Jesús", "Sánchez Bertolín", 18, "622731055", false, false);
        User admin = new User("admin", "123", "admin", "admin", 18, "123456789", true, false);
        User superadmin = new User("superadmin", "123", "superadmin", "superadmin", 18, "123456789", true, true);

        userDAO.insertUser(user);
        userDAO.insertUser(admin);
        userDAO.insertUser(superadmin);

        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy HH:mm" );

        Event event1 = new Event("Festival de rap","Batalla de gallos brutal", sdf.parse("06-12-2019 22:00"), new Long(200));
        Event event2 = new Event("Concieto de rock","Concierto de rock buenísimo", sdf.parse("013-12-2019 22:00"), new Long(100));
        Event event3 = new Event("Fiesta techno","Fiesta techno con un dj to wapo", sdf.parse("20-12-2019 22:00"), new Long(150));

        eventDAO.insertEvent(event1);
        eventDAO.insertEvent(event2);
        eventDAO.insertEvent(event3);
    }
}