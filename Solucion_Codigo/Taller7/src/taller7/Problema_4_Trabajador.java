package taller7;
/**
Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
* Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. 
* Además, existen diferentes tipos de trabajadores:

Fijos Mensuales: que cobran una cantidad fija al mes.
Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
Por Horas: cobran un precio por cada una de las horas que han realizado durante
* el mes. El precio es fijo para las primeras 40 horas y es otro para las horas
* realizadas a partir de la 40 hora mensual.
Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene
* obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno). 
* El programa debe permitir dar de alta a trabajadores, así como fijar horas 
* o ventas realizadas e imprimir la nómina correspondiente al final de mes.
* 
* @author Javier Vinan
 */
import java.util.ArrayList;

class Problema_4_Trabajador {

    public static void main(String[] args) {
        Jefe jefeGeneral = new Jefe("Ana", "Martínez", "Av. Central 123", "01234567A", 2500);
        jefeGeneral.jefe = jefeGeneral;

        Empresa empresa = new Empresa(jefeGeneral);

        FijosMensuales emp1 = new FijosMensuales("Juan", "Pérez", "Calle Uno 456", "00112233B", jefeGeneral, 1200);
        PorHoras emp2 = new PorHoras("María", "Gómez", "Calle Dos 789", "00445566C", jefeGeneral, 8.5, 42);
        Comisionistas emp3 = new Comisionistas("Luis", "Santos", "Calle Tres 321", "00778899D", jefeGeneral, 7000, 0.04);

        empresa.darDeAltaTrabajador(emp1);
        empresa.darDeAltaTrabajador(emp2);
        empresa.darDeAltaTrabajador(emp3);

        empresa.listarNomina();
    }
    String nombres;
    String apellidos;
    String direccion;
    String DNI;
    Jefe jefe;

    public Problema_4_Trabajador(String nombres, String apellidos, String direccion, String DNI, Jefe jefe) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.jefe = jefe;
    }

    public double calcularSueldo() {
        return 0;
    }

    public String toString() {
        return "Trabajador: " + nombres + " " + apellidos + " (" + DNI + ")\n"
                + "Dirección: " + direccion + "\n"
                + "Jefe: " + jefe.nombres + " " + jefe.apellidos + "\n"
                + "Sueldo: " + calcularSueldo() + "\n"
                + "----------------------------------";
    }
}

class Empresa {

    Jefe jefeGeneral;
    ArrayList<Problema_4_Trabajador> trabajadores;

    public Empresa(Jefe jefeGeneral) {
        this.jefeGeneral = jefeGeneral;
        trabajadores = new ArrayList<>();
        trabajadores.add(jefeGeneral);
    }

    public void darDeAltaTrabajador(Problema_4_Trabajador t) {
        trabajadores.add(t);
    }

    public void listarNomina() {
        System.out.println("NOMINA DE LA EMPRESA");
        for (Problema_4_Trabajador t : trabajadores) {
            System.out.println(t);
        }
    }
}

class Jefe extends Problema_4_Trabajador {

    double sueldo;

    public Jefe(String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, null);
        this.sueldo = sueldo;
    }

    public double calcularSueldo() {
        return sueldo;
    }
}

class FijosMensuales extends Problema_4_Trabajador {

    double sueldo;

    public FijosMensuales(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, double sueldo) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.sueldo = sueldo;
    }

    public double calcularSueldo() {
        return sueldo;
    }
}

class PorHoras extends Problema_4_Trabajador {

    double valorHora;
    int horasTrabajadas;

    public PorHoras(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, double valorHora, int horasTrabajadas) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.valorHora = valorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    public double calcularSueldo() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * valorHora;
        } else {
            return 40 * valorHora + (horasTrabajadas - 40) * (valorHora * 1.5);
        }
    }
}

class Comisionistas extends Problema_4_Trabajador {

    int ventasMes;
    double comision;

    public Comisionistas(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, int ventasMes, double comision) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.ventasMes = ventasMes;
        this.comision = comision;
    }

    public double calcularSueldo() {
        return ventasMes * comision;
    }
}
