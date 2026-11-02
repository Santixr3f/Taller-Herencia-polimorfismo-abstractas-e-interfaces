package envios.app;

import java.util.ArrayList;

import envios.modelo.Asegurable;
import envios.modelo.Bodega;
import envios.modelo.Camion;
import envios.modelo.Moto;
import envios.modelo.Vehiculo;

/** Parte B: retos. Las líneas que no compilan quedan comentadas con su número de reto. */
public class Retos {
    public static void main(String[] args) {
        Camion c1 = new Camion("WFT345", 8000, 2);

        // Reto 1: no compila (Vehiculo es abstracta)
        // Vehiculo x = new Vehiculo("XYZ999", 50);

        // Reto 3: no compila (w es de tipo Vehiculo y no tiene getEjes())
        // Vehiculo w = c1;
        // System.out.println(w.getEjes());
        System.out.println("=== Reto 3 (corregido) ===");
        Vehiculo w = c1;
        if (w instanceof Camion) {
            System.out.println(((Camion) w).getEjes());
        }

        // Reto 4: sobrecarga
        System.out.println("=== Reto 4 ===");
        System.out.println(c1.costoEnvio(10, true));
        System.out.println(c1.costoEnvio(10, false));

        // Reto 6: predicción
        System.out.println("=== Reto 6 ===");
        Vehiculo v = new Moto("AAA11A", 20, 100);
        System.out.println(v.describir());
        System.out.println(v.costoEnvio(10));
        System.out.println(v instanceof Asegurable);
        System.out.println(v instanceof Camion);
        Asegurable a = new Bodega("Bodega Norte", 50);
        System.out.println(a.valorSeguro());
    }
}
