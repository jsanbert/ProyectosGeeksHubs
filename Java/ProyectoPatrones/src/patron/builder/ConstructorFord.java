package patron.builder;

public class ConstructorFord extends ConstructorCoche {

    public ConstructorFord() {
        super.nuevoCoche();
    }

    public void construirCaracteristicas() {
        super.getCoche()
                .caracteristicas(200,8,4)
                .modeloGama("Fiesta", "Ford", "Media");
    }
}
