# Taller: Herencia, polimorfismo, abstractas e interfaces

Universidad Autónoma de Manizales · Fundamentos de Programación Orientada a Objetos

**Estudiante:** TOMAS SANTIAGO GOMEZ SARMIENTO Y VLADIMIR SANTIAGO FLOREZ ACEVEDO

---

## Parte B. Retos

### Reto 1. `new Vehiculo("XYZ999", 50)`

**Error copiado:**

```
Retos.java:17: error: Vehiculo is abstract; cannot be instantiated
        Vehiculo x = new Vehiculo("XYZ999", 50);
                     ^
1 error
```

**Explicación:** Vehiculo es una clase abstracta (tiene tarifaPorKm() sin implementación). Una clase abstracta está incompleta a propósito: no sabe cuánto cobra un "vehículo en general", así que Java no permite crear objetos directamente con new. Solo se pueden crear objetos de sus subclases concretas (Moto, Camion, Bicicleta), que sí completan el método faltante. Sí se puede declarar una variable de tipo Vehiculo, pero apuntando a un objeto de una subclase.

### Reto 2. Quitar `tarifaPorKm()` de `Bicicleta`

**Error copiado:**

```
Bicicleta.java:3: error: Bicicleta is not abstract and does not override abstract method tarifaPorKm() in Vehiculo
public class Bicicleta extends Vehiculo {
       ^
1 error
```

**Explicación:** Bicicleta hereda de Vehiculo el método abstracto tarifaPorKm(). Una clase concreta tiene la obligación de implementar todos los métodos abstractos que hereda. Si no lo hace, Java la considera incompleta y solo permitiría que fuera declarada abstract. Es justamente la garantía de la clase abstracta: cualquier subclase concreta _tiene_ que definir su tarifa. El método se dejó de nuevo en Bicicleta (tarifa 600).

### Reto 3. `Vehiculo w = c1; System.out.println(w.getEjes());`

**Error copiado:**

```
Retos.java:21: error: cannot find symbol
        System.out.println(w.getEjes());
                            ^
  symbol:   method getEjes()
  location: variable w of type Vehiculo
1 error
```

**Explicación:** el compilador revisa los métodos según el **tipo de la variable** (Vehiculo), no según el objeto real (Camion). Vehiculo no tiene getEjes(); ese método solo existe en Camion. Aunque en ejecución w apunte a un camión, el compilador no lo sabe.

**Corrección** (sin cambiar el tipo de w): preguntar con instanceof y hacer un casteo.

```java
Vehiculo w = c1;
if (w instanceof Camion) {
    System.out.println(((Camion) w).getEjes());   // imprime 2
}
```

### Reto 4. `costoEnvio(double km, boolean urgente)`

```java
public double costoEnvio(double km, boolean urgente) {
    double costo = costoEnvio(km);
    return urgente ? costo * 1.5 : costo;
}
```

Salida:

```
75000.0    // c1.costoEnvio(10, true)  -> 10 * 5000 * 1.5
50000.0    // c1.costoEnvio(10, false) -> 10 * 5000
```

**¿Sobrecarga o sobrescritura?** Es **sobrecarga** (_overload_). Se mantiene el mismo nombre `costoEnvio` pero con una **lista de parámetros distinta** (`double` frente a `double, boolean`), y ambos métodos conviven en la misma clase. Java elige cuál ejecutar en tiempo de compilación según los argumentos. La sobrescritura (_override_) sería redefinir en una subclase un método con **la misma firma** que el del padre (como hacen `Moto` y `Camion` con `describir()`), y se resuelve en ejecución según el objeto real.

### Reto 5. Borrar `@Override` y renombrar a `describr` en `Bicicleta`

**¿Qué cambió en la salida?** La bicicleta se muestra sin su descripción propia:

```
Envíos Caldas:
- MZL12A (20 kg), moto de 150 cc
- WFT345 (8000 kg), camión de 2 ejes
- KJH78B (25 kg), moto de 200 cc
- BIC001 (10 kg)                      <-- antes: BIC001 (10 kg), bicicleta eléctrica
```

**¿Por qué compiló?** Sin `@Override`, `describr()` (con el error de digitación) es simplemente un método **nuevo** de `Bicicleta`, no una sobrescritura. Es un método válido, aunque nadie lo llama. Entonces `mostrar()` llama a `describir()` y, como `Bicicleta` ya no lo sobrescribe, ejecuta la versión heredada de `Vehiculo`, que no agrega ", bicicleta eléctrica". Es un error silencioso de lógica: no hay mensaje del compilador, solo un resultado incorrecto.

**¿Qué habría pasado con `@Override`?** El compilador habría detectado el error de inmediato:

```
Bicicleta.java:9: error: method does not override or implement a method from a supertype
    @Override
    ^
1 error
```

`@Override` es una promesa de "este método sobrescribe uno del padre"; si no es cierto, Java avisa. Por eso conviene usarlo siempre. El código se dejó como estaba (con `@Override` y `describir`).

### Reto 6. Predicción

```java
Vehiculo v = new Moto("AAA11A", 20, 100);
System.out.println(v.describir());
System.out.println(v.costoEnvio(10));
System.out.println(v instanceof Asegurable);
System.out.println(v instanceof Camion);
Asegurable a = new Bodega("Bodega Norte", 50);
System.out.println(a.valorSeguro());
```

**Predicción (antes de ejecutar):**

1. `v.describir()` → `AAA11A (20 kg), moto de 100 cc`. Polimorfismo: aunque `v` sea de tipo `Vehiculo`, se ejecuta el `describir()` de `Moto`.
2. `v.costoEnvio(10)` → `12000.0`. Es 10 × `tarifaPorKm()` de Moto (1200).
3. `v instanceof Asegurable` → `true`. El objeto real es una `Moto`, que implementa `Asegurable`.
4. `v instanceof Camion` → `false`. Es una `Moto`, no un `Camion` (compila porque `Camion` es subclase de `Vehiculo`).
5. `a.valorSeguro()` → `100000.0`. Son 50 metros × 2000.

**Comprobación (salida real):**

```
AAA11A (20 kg), moto de 100 cc
12000.0
true
false
100000.0
```

La predicción coincidió con la ejecución.

---

## Parte C. Preguntas

1. ¿Por qué Bicicleta funcionó en mostrar() y en masEconomico() sin cambiar una sola línea de Flota?

Porque Flota trabaja con el tipo Vehiculo, no con tipos concretos. mostrar() solo llama a describir() y masEconomico() solo usa getCapacidadKg() y tarifaPorKm(), y esos métodos existen en Vehiculo. Bicicleta es un Vehiculo, así que puede entrar en la lista, y en ejecución Java llama a la versión de Bicicleta (polimorfismo / enlace dinámico). Flota queda abierta a nuevos tipos sin modificarse.

2. ¿Por qué Vehiculo es abstracta y Moto no? ¿Qué pasaría si Moto también lo fuera?

Vehiculo es un concepto general: no existe un "vehículo genérico" ni se puede saber su tarifa, por eso deja tarifaPorKm() sin implementar y no se puede instanciar. Moto es un caso concreto: implementa todos los métodos y representa objetos reales (MZL12A, KJH78B), por eso es concreta.

Si Moto también fuera abstracta, no se podría hacer new Moto(...): PruebaHerencia y Main dejarían de compilar con el mismo error del Reto 1, y no habría motos en la flota. Habría que crear otra subclase concreta de Moto, lo que no tiene sentido porque Moto ya tiene todo lo necesario.

3. ¿Por qué Bodega implementa Asegurable pero no hereda de Vehiculo?

Porque una bodega no es un vehículo: no tiene placa, ni capacidad en kg, ni tarifa por kilómetro, y no se mueve. Heredar de Vehiculo sería una relación "es un" falsa y la obligaría a tener atributos y métodos sin sentido. En cambio, "poder asegurarse" es una capacidad que puede compartir cualquier clase, esté o no en la misma jerarquía. La interfaz Asegurable permite que una Moto, un Camion y una Bodega se traten de forma uniforme (como en la lista ArrayList<Asegurable>), y Bicicleta no la implementa porque no se asegura. Además, Java solo permite herencia simple de clases, pero una clase puede implementar varias interfaces.

4. ¿Qué diferencia hay entre super(placa, capacidadKg) y super.describir()?
super(placa, capacidadKg) es una llamada al constructor de la superclase. Solo puede ir como primera instrucción del constructor de la subclase y sirve para inicializar los atributos heredados (en este caso, privados, que la subclase no puede asignar directamente).
super.describir() es una llamada a un método de la superclase, la versión que fue sobrescrita. Se puede usar en cualquier punto de un método de la subclase y sirve para reutilizar el comportamiento del padre y extenderlo (por ejemplo, Moto obtiene "MZL12A (20 kg)" y le agrega ", moto de 150 cc").
5. Cierre del curso: ¿qué es la POO y un ejemplo de cada pilar en el código?

La Programación Orientada a Objetos es una forma de construir programas modelando el problema como un conjunto de objetos que tienen datos (atributos) y comportamientos (métodos) y que colaboran entre sí. En lugar de escribir un solo flujo de instrucciones, se definen clases que representan cosas del problema (vehículos, flotas, bodegas) y se organizan con relaciones entre ellas, lo que facilita reutilizar, mantener y ampliar el código.

Abstracción: Vehiculo (clase abstracta) y la interfaz Asegurable capturan solo lo esencial ("un vehículo tiene placa, capacidad y tarifa por km"; "algo asegurable tiene un valor de seguro") y dejan los detalles a las subclases. tarifaPorKm() declara qué se necesita sin decir cómo.

Encapsulamiento: en todas las clases los atributos son private (placa, capacidadKg, cilindraje, ejes, vehiculos…) y se accede con métodos públicos como getPlaca() o agregar(). Flota protege su lista: nadie puede modificarla sin pasar por agregar(), que evita nulos y repetidos.

Herencia: Moto, Camion y Bicicleta heredan de Vehiculo (extends), reutilizando placa, capacidadKg, getPlaca() y costoEnvio(); cada una agrega lo propio (cilindraje, ejes).

Polimorfismo: en Flota.mostrar(), un único for-each sobre Vehiculo llama a describir() y cada objeto responde a su manera (moto, camión o bicicleta). Lo mismo ocurre con costoEnvio(), que usa la tarifaPorKm() de cada subclase, y con ArrayList<Asegurable>, donde Camion y Bodega responden distinto a valorSeguro().
