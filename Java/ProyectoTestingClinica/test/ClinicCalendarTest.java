package test;

import hospital.ClinicCalendar;
import hospital.Doctor;
import hospital.PatientAppointment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {

    static ClinicCalendar clinicCalendar;

    @BeforeEach
    static void init() {
        System.out.println("Inicializando clinic calendar");
        clinicCalendar = new ClinicCalendar();
    }

    @Test
    void testCreateAppointment() {
        clinicCalendar.addAppointment("Pepe", "Martínez", "johnson", "12/10/2019 10:50 am");
        List<PatientAppointment> appointments = clinicCalendar.getAppointments();
        assertEquals(1, appointments.size());
        appointments.forEach(a -> assertNotNull(a));
    }

    @Test
    void testHasAppointment() {
        clinicCalendar.addAppointment("Pepe", "Martínez", "johnson", "12/10/2019 10:50 am");
        assertTrue(clinicCalendar.hasAppointment("Pepe", "Martínez"));
        assertFalse(clinicCalendar.hasAppointment("Ana", "Pérez"));
    }

    @Test
    void testAssertAll() {
        clinicCalendar.addAppointment("Pepe", "Martínez", "johnson", "12/10/2019 10:50 am");
        assertAll("Test conjunto: crear cita y tener cita",
                () -> assertEquals(1, clinicCalendar.getAppointments().size()),
                () -> clinicCalendar.getAppointments().forEach(a -> assertNotNull(a)),
                () -> assertTrue(clinicCalendar.hasAppointment("Pepe", "Martínez")),
                () -> assertFalse(clinicCalendar.hasAppointment("Ana", "Pérez"))
        );
    }
}