package taller7;

/**
 * Dadas las siguientes clases, que expresan una relación de herencia entre las
 * entidades:
 *
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro. El
 * patio de butacas del teatro se divide en varias zonas, cada una identificada
 * por su nombre. Los datos de las zonas son los mostrados en la siguiente
 * tabla:
 *
 * NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO Principal	200
 * 25$	17.5$ PalcoB	40	70$	40$ Central	400	20$	14$ Lateral	100	15.5$	10$ Para
 * realizar la compra de una entrada, un espectador debe indicar la zona que
 * desea y presentar al vendedor el documento que justifique que tiene algún
 * tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la
 * entrada del tipo apropiado y de la zona indicada, en el momento de la compra
 * se asignará a la entrada un identificador (un número entero) que permitirá la
 * identificación de la entrada en todas las operaciones que posteriormente se
 * desee realizar con ella.
 *
 * Una entrada tiene como datos asociados su identificador, la zona a la que
 * pertenece y el nombre del comprador.
 *
 * Los precios de las entradas dependen de la zona y del tipo de entrada según
 * lo explicado a continuación:
 *
 * Entradas normales: su precio es el precio normal de la zona elegida sin
 * ningún tipo de descuento. Entradas reducidas (para estudiantes o
 * pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de
 * la zona elegida. Entradas abonado: su precio es el precio para abonados de la
 * zona elegida. La interacción entre el vendedor y la aplicación es la descrita
 * en los siguientes casos de usos.
 *
 * Note
 *
 * Caso de uso “Vende entrada”:
 *
 * El vendedor elige la opción “vende entrada” e introduce la zona deseada, el
 * nombre del espectador y el tipo (normal, abonado o beneficiario de entrada
 * reducida). Si la zona elegida no está completa, la aplicación genera una
 * nueva entrada con los datos facilitados. Si no existe ninguna zona con ese
 * nombre, se notifica y finaliza el caso de uso sin generar la entrada. Si la
 * zona elegida está completa lo notifica y finaliza el caso de uno sin generar
 * la entrada. La aplicación muestra el identificador y el precio de la entrada.
 * Caso de uso “Consulta entrada”:
 *
 * El vendedor elige la opción “consulta entrada” e introduce el identificador
 * de la entrada. La aplicación muestra los datos de la entrada: nombre del
 * espectador, precio y nombre de la zona. Si no existe ninguna entrada con ese
 * identificador, lo notifica y finaliza el caso de uso
 *
 * @author Javier Vinan
 */
import java.util.ArrayList;
public class Problema_5_SistemaTeatro {
    public static void main(String[] args) {
        Teatro teatro = new Teatro();

        teatro.venderEntrada("Principal", "Juan", "Normal");
        teatro.venderEntrada("PalcoB", "Ana", "Abonado");
        teatro.venderEntrada("Central", "Luis", "Reducida");

        System.out.println("\n=== Consultas ===");
        System.out.println(teatro.consultarEntrada(1));
        System.out.println(teatro.consultarEntrada(2));
        System.out.println(teatro.consultarEntrada(3));
    }
}

class Zona {
    public String nombre;
    public int disponibles;
    public int total;
    public double precioNormal;
    public double precioAbonado;

    public Zona(String nombre, int total, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.total = total;
        this.disponibles = total;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }
}


class Entrada {
    public int id;
    public String zona;
    public String comprador;
    public double precioBase;

    public Entrada(int id, String zona, String comprador, double precioBase) {
        this.id = id;
        this.zona = zona;
        this.comprador = comprador;
        this.precioBase = precioBase;
    }

    public double calcularPrecio() {
        return precioBase;
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, String zona, String comprador, double precioBase) {
        super(id, zona, comprador, precioBase);
    }

}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, String zona, String comprador, double precioBase) {
        super(id, zona, comprador, precioBase);
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, String zona, String comprador, double precioBase) {
        super(id, zona, comprador, precioBase);
    }

    @Override
    public double calcularPrecio() {
        return precioBase * 0.85;
    }
}


class Teatro {
    public ArrayList<Zona> zonas = new ArrayList<>();
    public ArrayList<Entrada> entradas = new ArrayList<>();
    public int nextId = 1;

    public Teatro() {

        zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        zonas.add(new Zona("Central", 400, 20.0, 14.0));
        zonas.add(new Zona("Lateral", 100, 15.5, 10.0));
    }

    public Entrada venderEntrada(String zonaNombre, String comprador, String tipo) {

        Zona zona = null;
        for (Zona z : zonas) {
            if (z.nombre.equals(zonaNombre)) {
                zona = z;
                break;
            }
        }

        if (zona == null) {
            System.out.println("Error: Zona no existe");
            return null;
        }
        if (zona.disponibles <= 0) {
            System.out.println("Error: Zona agotada");
            return null;
        }

        Entrada entrada = null;
        switch (tipo.toLowerCase()) {
            case "normal":
                entrada = new EntradaNormal(nextId, zona.nombre, comprador, zona.precioNormal);
                break;
            case "abonado":
                entrada = new EntradaAbonado(nextId, zona.nombre, comprador, zona.precioAbonado);
                break;
            case "reducida":
                entrada = new EntradaReducida(nextId, zona.nombre, comprador, zona.precioNormal);
                break;
            default:
                System.out.println("Error: Tipo de entrada no válido");
                return null;
        }

        entradas.add(entrada);
        zona.disponibles--;
        nextId++;

        System.out.println("Vendida: " + entrada.getClass().getSimpleName() + 
                          " #" + entrada.id + " - $" + entrada.calcularPrecio());
        return entrada;
    }

    public String consultarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.id == id) {
                return "Entrada #" + e.id + 
                       " | Tipo: " + e.getClass().getSimpleName() +
                       " | Zona: " + e.zona + 
                       " | Comprador: " + e.comprador + 
                       " | Precio: $" + e.calcularPrecio();
            }
        }
        return "Error: Entrada no encontrada";
    }
}