package envios.modelo;

public class Moto extends Vehiculo implements Asegurable {
    private int cilindraje;

    public Moto(String placa, int capacidadKg, int cilindraje) {
        super(placa, capacidadKg);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    @Override
    public String describir() {
        return super.describir() + ", moto de " + cilindraje + " cc";
    }

    @Override
    public double tarifaPorKm() {
        return 1200;
    }

    @Override
    public double valorSeguro() {
        return 80000 + cilindraje * 500;
    }
}
