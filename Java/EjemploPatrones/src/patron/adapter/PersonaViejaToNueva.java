package patron.adapter;

import java.time.LocalDate;

public class PersonaViejaToNueva implements INuevaPersona {
    private Persona p;

    public PersonaViejaToNueva(Persona p) {
        this.p = p;
    }

    @Override
    public LocalDate getFechaNacimiento() {
        return null;
    }

    @Override
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        // Conversión de fechas
        LocalDate date = LocalDate.now();
        int edad = date.getYear() - fechaNacimiento.getYear();
        this.p.setEdad(edad);
    }
}
