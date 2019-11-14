package patron.adapter;

import java.time.LocalDate;

public interface INuevaPersona {
    void setFechaNacimiento(LocalDate date);
    LocalDate getFechaNacimiento();
}