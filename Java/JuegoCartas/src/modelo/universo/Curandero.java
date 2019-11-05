package modelo.universo;

import principal.Main;

public class Curandero extends Carta {

    public Curandero() {
        super("modelo.universo.Curandero", Main.generarNumeroAleatorio(5, 10), MAX_SALUD);
        habilidadEspecial = "Curar";
        descripcionHabilidadEspecial = "Cura una cantidad aleatoria entre uno y tres cuartos del máximo de salud (" + MAX_SALUD + ") a la carta que selecciones";
    }

    // Cura a una carta herida
    @Override
    public void habilidadEspecial(Carta objetivo) {
        this.curar(objetivo);
    }

    public void curar(Carta objetivo) {
        if(objetivo.getSalud() == MAX_SALUD) {
            System.out.print("[" + this.tipo + "] intenta curar a [" + objetivo.getTipo() + "]... pero ya tiene el máximo de salud.");
        }
        else if(objetivo.estaVivo()) {
            int saludObjetivo = objetivo.getSalud();
            int puntosRegenerados = Main.generarNumeroAleatorio((MAX_SALUD / 4), (MAX_SALUD / 4) * 3);
            if(puntosRegenerados + saludObjetivo > MAX_SALUD)
                puntosRegenerados = MAX_SALUD - saludObjetivo;
            objetivo.setSalud(objetivo.getSalud() + puntosRegenerados);
            System.out.print("[" + this.tipo + "] cura a [" + objetivo.getTipo() + "] restaurando "
                    + puntosRegenerados + " puntos de salud (salud actual: " + objetivo.getSalud() + "/" + MAX_SALUD + ")");
        }
        else
            System.out.print("[" + this.tipo + "] intenta curar a [" + objetivo.getTipo() + "]... pero está muerto.");
    }

    public String getHabilidadEspecial() { return this.habilidadEspecial; }
}
