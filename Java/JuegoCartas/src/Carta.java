public abstract class Carta {
    public static final int MAX_SALUD = 100;
    public String tipo;
    public int ataque;
    public int salud;
    public boolean estaVivo;

    public Carta(String tipo, int ataque, int salud) {
        this.tipo = tipo;
        this.ataque = ataque;
        this.salud = salud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void atacar(Carta objetivo) {
        int saludObjetivo = objetivo.getSalud();
        if(saludObjetivo <= this.ataque)
            objetivo.matar();
        else
            objetivo.setSalud(saludObjetivo - this.ataque);

        System.out.print("[" + this.tipo + "] ataca a [" + objetivo.getTipo() + "] infligiendo [" + this.ataque + "] puntos de daño. ");
    }

    public int getSalud() {
        return this.salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void matar() {
        this.salud = 0;
        this.estaVivo = false;
    }

    public boolean estaVivo() {
        return this.estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public abstract void habilidadEspecial(Carta objetivo);

    public String toString() {
        return "Carta tipo [ " + this.tipo + " ], puntos ataque [ " + this.ataque + " ], salud [" + this.salud + "/" + MAX_SALUD + "]";
    }
}
