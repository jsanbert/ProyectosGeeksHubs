public class Medico extends Carta {

    public Medico() { super("Médico", 25, MAX_SALUD); }

    // Cura a una carta herida
    @Override
    public void habilidadEspecial(Carta objetivo) {
        this.curar(objetivo);
    }

    public void curar(Carta objetivo) {
        if(objetivo.estaVivo()) {
            objetivo.setSalud(MAX_SALUD);
            System.out.print("[" + this.tipo + "] cura a [" + objetivo.getTipo() + "] restaurando toda su salud.");
        }
        else
            System.out.print("[" + this.tipo + "] intenta curar a [" + objetivo.getTipo() + "]... pero está muerto.");
    }
}
