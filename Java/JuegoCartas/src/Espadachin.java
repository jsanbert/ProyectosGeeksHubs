public class Espadachin extends Carta {

    public Espadachin() {
        super("Espadachín", Main.generarNumeroAleatorio(15, 35), MAX_SALUD);
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

        System.out.print("Se inflige daño aumentado.");
    }
}