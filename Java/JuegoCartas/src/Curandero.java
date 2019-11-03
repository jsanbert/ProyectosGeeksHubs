public class Curandero extends Carta {

    public Curandero() { super("Curandero", Main.generarNumeroAleatorio(5, 10), MAX_SALUD); }

    // Cura a una carta herida
    @Override
    public void habilidadEspecial(Carta objetivo) {
        this.curar(objetivo);
    }

    public void curar(Carta objetivo) {
        if(objetivo.estaVivo()) {
            int puntosRegenerados = Main.generarNumeroAleatorio(25, MAX_SALUD);
            objetivo.setSalud(objetivo.getSalud() + puntosRegenerados);
            System.out.print("[" + this.tipo + "] cura a [" + objetivo.getTipo() + "] restaurando "
                            + puntosRegenerados + " puntos de salud (salud actual: " + objetivo.getSalud() + "/" + MAX_SALUD + ")");
        }
        else
            System.out.print("[" + this.tipo + "] intenta curar a [" + objetivo.getTipo() + "]... pero está muerto.");
    }
}
