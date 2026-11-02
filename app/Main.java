package envios.app;

import java.util.ArrayList;

import envios.modelo.Asegurable;
import envios.modelo.Bicicleta;
import envios.modelo.Bodega;
import envios.modelo.Camion;
import envios.modelo.Flota;
import envios.modelo.Moto;

/** Etapas 2, 3 y 4 en un solo main, en orden. */
public class Main {
    public static void main(String[] args) {
        Moto m1 = new Moto("MZL12A", 20, 150);
        Camion c1 = new Camion("WFT345", 8000, 2);
        Moto m2 = new Moto("KJH78B", 25, 200);

        // ---------- Etapa 2: polimorfismo con una lista ----------
        System.out.println("=== Etapa 2 ===");
        Flota flota = new Flota("Envíos Caldas");
        flota.agregar(m1);
        flota.agregar(c1);
        flota.agregar(m2);
        System.out.println(flota.agregar(m1));
        flota.mostrar();
        System.out.println(flota.cantidadCamiones());

        // ---------- Etapa 3: clase abstracta ----------
        System.out.println("=== Etapa 3 ===");
        System.out.println(m1.costoEnvio(12));
        System.out.println(c1.costoEnvio(12));
        Bicicleta b1 = new Bicicleta("BIC001", 10);
        flota.agregar(b1);
        flota.mostrar();
        System.out.println(flota.masEconomico(8).getPlaca());
        System.out.println(flota.masEconomico(22).getPlaca());
        System.out.println(flota.masEconomico(500).getPlaca());
        System.out.println(flota.masEconomico(9000));

        // ---------- Etapa 4: interfaz ----------
        System.out.println("=== Etapa 4 ===");
        System.out.println(m1.valorSeguro());
        System.out.println(c1.valorSeguro());
        System.out.println(flota.totalSeguros());

        ArrayList<Asegurable> asegurables = new ArrayList<>();
        asegurables.add(c1);
        asegurables.add(new Bodega("Bodega Chipre", 300));
        double suma = 0;
        for (Asegurable a : asegurables) {
            System.out.println(a.valorSeguro());
            suma += a.valorSeguro();
        }
        System.out.println(suma);
    }
}
