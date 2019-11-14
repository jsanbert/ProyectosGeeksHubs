package patron.builder;

public class ConstructorNissan extends ConstructorCoche {

    public ConstructorNissan() {
        super.nuevoCoche();
    }

    public void construirCaracteristicas() {
        super.getCoche()
                .caracteristicas(100,4,4)
                .modeloGama("Qasqai", "Nissan", "Alta");
    }
}
