package modelo.universo;

import principal.Main;

public abstract class Carta {
    public static final int MAX_SALUD = 100;
    public String tipo;
    public int ataque;
    public int defensa;
    public int salud;
    public String habilidadEspecial;
    public String descripcionHabilidadEspecial;

    public Carta(String tipo, int ataque, int defensa, int salud) {
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
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

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void atacar(Carta objetivo) {
        int saludObjetivo = objetivo.getSalud();
        int puntosDanyo = this.ataque - objetivo.getDefensa();
        if(puntosDanyo < 0)
            puntosDanyo = 5; // Hacer un mínimo de daño
        if(saludObjetivo <= puntosDanyo)
            objetivo.matar();
        else
            objetivo.setSalud(saludObjetivo - puntosDanyo);

        System.out.println("[" + this.tipo + "] ataca a [" + objetivo.getTipo() + "] infligiendo [" + puntosDanyo + "] puntos de daño. ");
        Main.esperarEnter(Main.ENTER_CONTINUAR);
    }

    public int getSalud() {
        return this.salud;
    }

    public void setSalud(int salud) {
        if(salud > MAX_SALUD)
            salud = MAX_SALUD;
        this.salud = salud;
    }

    public String getHabilidadEspecial() { return this.habilidadEspecial; }

    public String getDescripcionHabilidadEspecial() { return this.descripcionHabilidadEspecial; }

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
        String str = "Tipo: %-13s|%-3spuntos ataque: %-5d|%-3spuntos defensa: %-5d|%-3spuntos salud: %-3d/%-3d";
        str += (!this.estaVivo()) ? " (MUERTO)" : "";
        return str;
    }

    public int compararPorAtaque(Carta c) {
        return c.ataque - this.ataque;
    }

    public int compararPorDefensa(Carta c) {
        return c.defensa - this.defensa;
    }

    public int compararPorTipoAlfabeticamente(Carta c) {
        return this.tipo.compareTo(c.tipo);
    }
}
