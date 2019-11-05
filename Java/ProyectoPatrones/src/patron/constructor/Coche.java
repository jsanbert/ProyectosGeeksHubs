package patron.constructor;

public class Coche {
    public int potencia;
    private int cilindrada;
    public String marca;
    public String modelo;
    public String gama;
    public int numPuertas;

    public Coche(int potencia, String marca, String modelo, String gama, int numPuertas) {
        this.potencia = potencia;
        this.marca = marca;
        this.modelo = modelo;
        this.gama = gama;
        this.numPuertas = numPuertas;
    }

    public Coche() {

    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public Coche modeloGama(String modelo, String marca, String gama) {
        this.modelo = modelo;
        this.marca = marca;
        this.gama = gama;
        return this;
    }

    public Coche caracteristicas(int potencia, int cilindrada, int numPuertas) {
        this.potencia = potencia;
        this.cilindrada = cilindrada;
        return this;
    }
}
