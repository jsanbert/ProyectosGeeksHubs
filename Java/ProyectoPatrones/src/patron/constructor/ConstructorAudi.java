package patron.constructor;

public class ConstructorAudi extends ConstructorCoche {

    public ConstructorAudi() {
        super.nuevoCoche();
    }

    public void construirCaracteristicas() {
        super.getCoche()
                .caracteristicas(250,5000,5)
                .modeloGama("A5", "Audi", "Top");
    }
}
