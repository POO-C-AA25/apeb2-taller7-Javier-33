package taller7;
/**
 El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada
 * una de las cuentas consisten en el número de cuenta, el nombre del cliente y el balance 
 * actual. Escriba una clase para implementar dicha cuenta bancaria. El método constructor
 * debe aceptar como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos
 * para depositar o retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. 
* Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero
* una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad
* que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para 
* describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia. La
* clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular '
* el interés. Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo
* similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés del 10%, 
* sin cargos ni castigos por sobregiro.

Note

Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual se puSeda evidenciar
* el correcto funcionamiento de cada clase con n clientes, y que además se pueda mostrar el 
* balance (estado de cuenta) para cada cliente.

 * @author Javier Vinan
 */
public class Problema_6_SistemaBanco {

    public static void main(String[] args) { 
        CuentaCheques cuentaCheques = new CuentaCheques("987", "Antonio");
        CuentaAhorros cuentaAhorros = new CuentaAhorros("123", "Diego", 5);
        CuentaPlatino cuentaPlatino = new CuentaPlatino("232", "Javier");

        cuentaCheques.depositar(1000);
        cuentaCheques.retirar(500);
        System.out.println("Cuenta Cheques: "+cuentaCheques.toString());
        
        cuentaAhorros.depositar(2000);
        cuentaAhorros.calcularInteres();
        cuentaAhorros.retirar(500);
        System.out.println("Cuenta Ahorros: "+cuentaAhorros.toString());
        

        cuentaPlatino.depositar(3000);
        cuentaPlatino.calcularInteres();
        cuentaPlatino.retirar(500);
        System.out.println("Cuenta Platino: "+cuentaPlatino.toString());
        
    }
}

class CuentaBancaria { 

    public String numeroCuenta;
    public String nombreCliente;
    public double balance;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            balance += cantidad;
        }
    }
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
        }
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balance=" + balance + '}';
    }

}

class CuentaCheques extends CuentaBancaria {

    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

 

}

class CuentaAhorros extends CuentaBancaria {

    public double interes;

    public CuentaAhorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.interes = tasaInteres;
    }

    public void calcularInteres() {
        balance += balance * interes / 100;
    }

   @Override       
    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= balance) {
            balance -= cantidad;
        }
    }
}

class CuentaPlatino extends CuentaBancaria {

    public double interes = 10.0;

    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    public void calcularInteres() {
        balance += balance * interes / 100;
    }

  

}