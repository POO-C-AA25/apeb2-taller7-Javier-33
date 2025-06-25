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
import java.util.Random;
import java.util.Scanner;

public class Problema_5_SistemaTeatro {

    public static void main(String[] args) {
        ArrayList<Sector> sectores = new ArrayList<>();
        Sector vip = new Sector(25, 17.5, "VIP", 200);
        sectores.add(vip);
        Sector box = new Sector(70, 40, "Box", 40);
        sectores.add(box);
        Sector preferencia = new Sector(20, 14, "Preferencia", 400);
        sectores.add(preferencia);
        Sector general = new Sector(15.5, 10, "General", 100);
        sectores.add(general);

        int continuar = 0, tipoPrecio = 0, codigo = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<Boleto> vendidos = new ArrayList<>();

        while (continuar == 0) {
            System.out.println("\n\n--- SISTEMA DE BOLETOS TEATRO ---");
            System.out.println("1. Vender boleto");
            System.out.println("2. Buscar boleto por código");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Seleccione sector:");
                    System.out.println("[1]VIP\n[2]Box\n[3]Preferencia\n[4]General");
                    int opcionSector = scan.nextInt();
                    Sector sectorSeleccionado = sectores.get(opcionSector - 1);

                    if (sectorSeleccionado.hayEspacio()) {
                        System.out.print("Nombre de asistente: ");
                        String nombreAsistente = scan.next();

                        System.out.println("Tipo de boleto:");
                        System.out.println("[1]Regular\n[2]Suscriptor\n[3]Descuento");
                        tipoPrecio = scan.nextInt();

                        Boleto boleto = null;
                        switch (tipoPrecio) {
                            case 1:
                                boleto = new Regular(sectorSeleccionado, nombreAsistente);
                                break;
                            case 2:
                                boleto = new Suscriptor(sectorSeleccionado, nombreAsistente);
                                break;
                            case 3:
                                boleto = new Descuento(sectorSeleccionado, nombreAsistente);
                                break;
                        }
                        sectorSeleccionado.restarAsiento();
                        boleto.calcularCosto();
                        boleto.asignarCodigo();
                        vendidos.add(boleto);
                        System.out.println(boleto);
                    } else {
                        System.out.println("¡No quedan asientos en este sector!");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el código a buscar: ");
                    codigo = scan.nextInt();
                    boolean encontrado = false;
                    for (Boleto b : vendidos) {
                        if (b.codigo == codigo) {
                            System.out.println("\n>> Asistente: " + b.nombreComprador);
                            System.out.println(">> Costo: " + b.costo);
                            System.out.println(">> Sector: " + b.sector.nombreSector + "\n");
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No se encontró el boleto.");
                    }
                    break;
                case 3:
                    continuar = 1;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scan.close();
    }
}

class Sector {

    public double precioBase;
    public double precioSuscriptor;
    public String nombreSector;
    public int asientos;

    public Sector(double precioBase, double precioSuscriptor, String nombreSector, int asientos) {
        this.precioBase = precioBase;
        this.precioSuscriptor = precioSuscriptor;
        this.nombreSector = nombreSector;
        this.asientos = asientos;
    }

    public boolean hayEspacio() {
        return this.asientos > 0;
    }

    public void restarAsiento() {
        this.asientos -= 1;
    }
}

class Boleto {

    public Sector sector;
    public int codigo;
    public String nombreComprador;
    public double costo;

    public Boleto(Sector sector, String nombreComprador) {
        this.sector = sector;
        this.nombreComprador = nombreComprador;
    }

    public double calcularCosto() {
        this.costo = this.sector.precioBase;
        return costo;
    }

    public void asignarCodigo() {
        Random aleatorio = new Random();
        int num = 10000 + aleatorio.nextInt(90000);
        this.codigo = num;
    }

    public String toString() {
        return "Boleto{" + "codigo=" + codigo + ", costo=" + costo + '}';
    }
}

class Regular extends Boleto {

    public Regular(Sector sector, String nombreComprador) {
        super(sector, nombreComprador);
    }
}

class Descuento extends Boleto {

    public Descuento(Sector sector, String nombreComprador) {
        super(sector, nombreComprador);
    }

    public double calcularCosto() {
        this.costo = super.calcularCosto() * 0.85;
        return costo;
    }
}

class Suscriptor extends Boleto {

    public Suscriptor(Sector sector, String nombreComprador) {
        super(sector, nombreComprador);
    }

    public double calcularCosto() {
        this.costo = this.sector.precioSuscriptor;
        return costo;
    }
}
