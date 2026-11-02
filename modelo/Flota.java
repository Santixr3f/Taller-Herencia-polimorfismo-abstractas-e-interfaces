package envios.modelo;

import java.util.ArrayList;

public class Flota {
    private String nombre;
    private ArrayList<Vehiculo> vehiculos;

    public Flota(String nombre) {
        this.nombre = nombre;
        this.vehiculos = new ArrayList<>();
    }

    public boolean agregar(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculos.contains(vehiculo)) {
            return false;
        }
        vehiculos.add(vehiculo);
        return true;
    }

    /** Un solo for-each, sin preguntar la clase: polimorfismo. */
    public void mostrar() {
        System.out.println(nombre + ":");
        for (Vehiculo v : vehiculos) {
            System.out.println("- " + v.describir());
        }
    }

    public int cantidadCamiones() {
        int cantidad = 0;
        for (Vehiculo v : vehiculos) {
            if (v instanceof Camion) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /** Entre los que soportan el peso, el de menor tarifa; null si ninguno sirve. */
    public Vehiculo masEconomico(int pesoKg) {
        Vehiculo mejor = null;
        for (Vehiculo v : vehiculos) {
            if (v.getCapacidadKg() >= pesoKg
                    && (mejor == null || v.tarifaPorKm() < mejor.tarifaPorKm())) {
                mejor = v;
            }
        }
        return mejor;
    }

    /** Suma el seguro solo de los vehículos que son Asegurable. */
    public double totalSeguros() {
        double total = 0;
        for (Vehiculo v : vehiculos) {
            if (v instanceof Asegurable) {
                total += ((Asegurable) v).valorSeguro();
            }
        }
        return total;
    }
}
