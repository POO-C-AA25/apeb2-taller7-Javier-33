package taller7;
/**
Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes
* que se pueden enviar entre móviles, mensajes de texto (SMS) y mensajes que contienen 
* imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje en 
* caracteres que se desea enviar de un móvil a otro. Por otro lado, los mensajes 
* que contienen imágenes almacenan información sobre la imagen a enviar, la cual 
* se representará por el nombre del fichero que la contiene. Independientemente del 
* tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un 
* destinatario. Ambos estarán definidos obligatoriamente por un número de móvil, y 
* opcionalmente se podrá guardar información sobre su nombre. Además, los métodos
* enviarMensaje y visualizarMensaje deben estar definidos.

* @author Javier Vinan
 */

class Problema_3_Mensaje {
    
    public static void main(String[] args) {
        SMS sms1 = new SMS("Hola, soy Javier", "0999888777", "0988765432");
        MMS mms1 = new MMS("vacaciones.jpg", "0999000111", "0988000222");

        sms1.enviarMensaje();
        sms1.visualizarMensaje();

        System.out.println("");
        
        mms1.enviarMensaje();
        mms1.visualizarMensaje();

    }
    String remitente;
    String destinatario;

    public Problema_3_Mensaje(String remitente, String destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public void enviarMensaje() {
        System.out.println("Mensaje enviado de " + remitente + " a " + destinatario);
    }

    public void visualizarMensaje() {
        System.out.println("Mensaje de " + remitente + " para " + destinatario);
    }
}

class SMS extends Problema_3_Mensaje {
    String caracteres;

    public SMS(String caracteres, String remitente, String destinatario) {
        super(remitente, destinatario);
        this.caracteres = caracteres;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("SMS enviado de " + remitente + " a " + destinatario + ": " + caracteres);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("SMS: " + caracteres);
    }
}

class MMS extends Problema_3_Mensaje {
    String nombreFichero;

    public MMS(String nombreFichero, String remitente, String destinatario) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("MMS enviado de " + remitente + " a " + destinatario + ": Imagen [" + nombreFichero + "]");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("MMS: Imagen [" + nombreFichero + "]");
    }
}

