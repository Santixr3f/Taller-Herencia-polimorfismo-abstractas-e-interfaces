package envios.modelo;

public class Bicicleta extends Vehiculo {

    public Bicicleta(String placa, int capacidadKg) {
        super(placa, capacidadKg);
    }

    @Override
    public String describir() {
        return super.describir() + ", bicicleta eléctrica";
    }

    @Override
    public double tarifaPorKm() {
        return 600;
    }
}
