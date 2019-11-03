public abstract class Carta {
    public static final int MAX_SALUD = 100;
    public String tipo;
    public int ataque;
    public int salud;

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

        System.out.println("[" + this.tipo + "] ataca a [" + objetivo.getTipo() + "] infligiendo [" + this.ataque + "] puntos de daño. ");
    }

    public int getSalud() {
        return this.salud;
    }

    public void setSalud(int salud) {
        if(salud > MAX_SALUD)
            salud = MAX_SALUD;
        this.salud = salud;
    }

    public void matar() {
        this.salud = 0;
    }

    public boolean estaVivo() {
        return this.salud > 0;
    }

    public abstract void habilidadEspecial(Carta objetivo);

    public String toString() {
        String str = "Tipo: [" + this.tipo + "], puntos ataque: [" + this.ataque + "]";
        str += (!this.estaVivo()) ? "(MUERTO)" : ", salud: [" + this.salud + "/" + MAX_SALUD + "]";
        return str;
    }

    public String toStringFormatted() {
        String str = "Tipo: %-10s %-6spuntos ataque: %-3d %-6spuntos salud: %-3d/%-3d";
        str += (!this.estaVivo()) ? "%-3s(MUERTO)" : "";
        return str;
    }
}
