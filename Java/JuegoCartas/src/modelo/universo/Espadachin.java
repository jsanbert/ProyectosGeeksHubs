package modelo.universo;

import principal.Main;

public class Espadachin extends Carta {

    public Espadachin() {
        super("Espadachín", Main.generarNumeroAleatorio(20, 30), Main.generarNumeroAleatorio(10, 15), MAX_SALUD);
        habilidadEspecial = "Espadazo";
        descripcionHabilidadEspecial = "Inflige un gran daño adicional, desde el doble hasta una cantidad aleatoria máxima de 15 puntos";
    }

    // Inflige doble de daño
    @Override
    public void habilidadEspecial(Carta objetivo) {
        this.espadazo(objetivo);
    }

    public void espadazo(Carta objetivo) {
        int ataqueAntiguo = this.ataque;
        this.setAtaque(this.ataque * 2 + ((int) Math.random() * 15));
        this.atacar(objetivo);
        this.setAtaque(ataqueAntiguo);
    }
}