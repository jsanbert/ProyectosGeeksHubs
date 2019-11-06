package modelo.universo;

import principal.Main;

public class Mago extends Carta {
    public Mago() {
        super("Mago", Main.generarNumeroAleatorio(10, 20), MAX_SALUD);
        habilidadEspecial = "Resucitar";
        descripcionHabilidadEspecial = "Resucita a una carta que esté muerta, con la mitad de salud";
    }

    // Resucita una carta muerta
    @Override
    public void habilidadEspecial(Carta objetivo) { this.resucitar(objetivo); }

    public void resucitar(Carta objetivo) {
        if(!objetivo.estaVivo()) {
            objetivo.setSalud(MAX_SALUD / 2);
            System.out.print("[" + this.tipo + "] resucita a [" + objetivo.getTipo() + "]");
        } else
            System.out.print("[" + this.tipo + "] intenta resucitar a [" + objetivo.getTipo() + "]... pero ya está vivo.");
    }
}
