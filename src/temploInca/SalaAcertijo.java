package temploInca;

import java.util.Scanner;

public class SalaAcertijo extends Sala {

    private String pregunta;
    private String solucion;
    private String pista;

    public SalaAcertijo(String nombre, String descripcion, String itemRecompensa, String pregunta, String solucion,
            String pista) {
        super(nombre, descripcion, itemRecompensa);
        this.pregunta = pregunta;
        this.solucion = solucion;
        this.pista = pista;
    }

    @Override
    public boolean resolver(Jugador jugador, Scanner sc) {
        mostrarHistoria();
        System.out.println("RETO: " + this.pregunta);
        System.out.println("*(Comandos: 'inventario' | 'mapa' | 'pista')*");

        while (jugador.estaVivo() && this.estado != EstadoSala.COMPLETADA) {
            System.out.print("> Tu respuesta: ");
            String respuesta = sc.nextLine().trim().toLowerCase();

            if (respuesta.equalsIgnoreCase("inventario")) {
                jugador.mostrarInventario();

            } else if (respuesta.equalsIgnoreCase("mapa")) {
                System.out.println("Te encuentras en: " + this.nombre);
                System.out.println("Energía actual: " + jugador.getEnergia());

            } else if (respuesta.equalsIgnoreCase("pista")) {

                System.out.println("Pista: " + this.pista);

            }

            else if (respuesta.equalsIgnoreCase(this.solucion)) {
                System.out.println("¡Correcto! El camino se abre.");

                if (this.itemRecompensa != null) {
                    jugador.recogerItem(this.itemRecompensa);
                }

                this.setEstado(EstadoSala.COMPLETADA);
                return true;

            }

            else {
                System.out.println("X Respuesta incorrecta...");
                jugador.gastarEnergia(1);

                if (jugador.estaVivo()) {
                    System.out.println("Inténtalo de nuevo.");
                }
            }
        }
        return false;
    }
}