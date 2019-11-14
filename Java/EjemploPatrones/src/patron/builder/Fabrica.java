package patron.builder;

public class Fabrica {
    private ConstructorCoche constructorCoches;

    public static final String COCHE_AUDI = "AUDI";
    public static final String COCHE_NISSAN = "NISSAN";
    public static final String COCHE_FORD = "FORD";

    public Fabrica(ConstructorCoche constructorCoche) {
        this.constructorCoches = constructorCoche;
    }

    public Fabrica(String tipo) {
        switch(tipo) {
            case COCHE_AUDI:
                this.constructorCoches = new ConstructorAudi();
                break;
            case COCHE_NISSAN:
                this.constructorCoches = new ConstructorNissan();
                break;
            case COCHE_FORD:
                this.constructorCoches = new ConstructorFord();
                break;
        }
    }

    public void construirCoche() {
        constructorCoches.construirCaracteristicas();
    }
}
