package envios.modelo;

/**
 * Clase abstracta base de todos los vehículos de la flota.
 * Define los datos comunes (placa, capacidad) y obliga a cada subclase
 * a decir cuánto cobra por kilómetro (tarifaPorKm).
 */
public abstract class Vehiculo {
    private String placa;
    private int capacidadKg;

    public Vehiculo(String placa, int capacidadKg) {
        this.placa = placa;
        this.capacidadKg = capacidadKg;
    }

    public String getPlaca() {
        return placa;
    }

    public int getCapacidadKg() {
        return capacidadKg;
    }

    public String describir() {
        return placa + " (" + capacidadKg + " kg)";
    }

    /** Cada tipo de vehículo define su propia tarifa. */
    public abstract double tarifaPorKm();

    /** Método concreto que usa el abstracto (polimorfismo). */
    public double costoEnvio(double km) {
        return km * tarifaPorKm();
    }

    /** Sobrecarga (Reto 4): mismo nombre, distinta lista de parámetros. */
    public double costoEnvio(double km, boolean urgente) {
        double costo = costoEnvio(km);
        return urgente ? costo * 1.5 : costo;
    }
}
