public class Espadachin extends Carta {

    public Espadachin() {
        super("Espadachín", 25, MAX_SALUD);
    }

    // Inflige doble de daño
    @Override
    public void habilidadEspecial(Carta objetivo) {
        this.espadazo(objetivo);
    }

    public void espadazo(Carta objetivo) {
        int aux = this.ataque;
        this.setAtaque(this.ataque * 2);
        this.atacar(objetivo);
        this.setAtaque(aux);

        System.out.print("Se inflige el doble de daño.");
    }
}
