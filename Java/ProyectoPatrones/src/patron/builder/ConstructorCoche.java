package patron.builder;

public abstract class ConstructorCoche {
    private Coche coche;

    public ConstructorCoche() {
    }

    public Coche nuevoCoche() {
        coche = new Coche();
        return coche;
    }

    public Coche getCoche() {
        return this.coche;
    }

    public abstract void construirCaracteristicas();
}
