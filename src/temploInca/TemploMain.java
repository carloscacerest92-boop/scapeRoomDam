package temploInca;

import java.util.Scanner;
import java.util.ArrayList;

public class TemploMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("          EL MISTERIO DEL TEMPLO INCA");
        System.out.println("=================================================");
        System.out.print("Introduce tu nombre, explorador: ");
        String nombreJugador = sc.nextLine().trim();

        Jugador jugador = new Jugador(nombreJugador);

        ArrayList<Sala> mapa = new ArrayList<>();

        mapa.add(new SalaAcertijo("1. Entrada del Templo", "Una enorme puerta bloquea el paso.", null,
                "¿Quién es el Dios Sol Inca?", "inti", "¿Cómo se dice sol en quechua?"));
        mapa.add(new SalaAcertijo("2. Salón del Conocimiento", "Muros llenos de matemáticas antiguas.",
                "01 Piedra Solar", "¿Cuáles son los 4 numeros Sagrados de la Sala", "2357",
                "Piensa en los números que solo se dividen por 1 y por sí mismos"));
        mapa.add(new SalaAcertijo("3. Pasillo de los Ecos", "Se escucha un murmullo constante.", null,
                "Completa: _ _ _ _ A M A M A", "pachamama", "Tierra en QUECHUA"));
        mapa.add(new SalaAcertijo("4. Sala de las Estaciones", "Hay cuatro estatuas de piedra.", "03 Llave de Bronce",
                "Dime el nombre de la estatua de fuego", "verano",
                "Tiene que ver con el nombre de la sala, y donde la temperatura es mayor!"));

        mapa.add(new TresEnRaya("5. Sala del Desafío Solar", "El espíritu Amaru te bloquea el camino.",
                "05 Símbolo de Amaru"));

        mapa.add(new SalaAcertijo("6. Biblioteca Rocosa", "Cientos de rocas grabadas con animales.", null,
                "¿Qué animal andino escupe si se enfada?", "llama", "Es un camélido muy peludo y típico de Perú."));
        mapa.add(new SalaAcertijo("7. Altar de los Sacrificios", "Un altar misterioso te hace una pregunta...", null,
                "¿De qué color es la sangre que pide el altar?", "rojo", "Es el color del fuego y la pasión."));
        mapa.add(new SalaAcertijo("8. Cámara Dorada Final", "Un cofre gigante con un candado numérico te espera.",
                "[Legendario]Llave de la Sabiduria>>>Te permite salir del templo Inca",
                "Introduce el código final de 3 digitos:", "135", "Tu inventario puede guardar la pista!>"));

        System.out.println("\n¡Bienvenido " + jugador.getNombre() + "! Tienes " + jugador.getEnergia()
                + " puntos de energía para escapar.");
        System.out.println("Pulsa ENTER para adentrarte en la oscuridad...");
        sc.nextLine();

        try {
            for (Sala salaActual : mapa) {
                if (jugador.estaVivo()) {
                    salaActual.resolver(jugador, sc);
                }
            }

            System.out.println("\n=================================================");
            System.out.println("¡FELICIDADES " + jugador.getNombre() + "! Has escapado del templo con vida.");
            jugador.mostrarInventario();
            System.out.println("=================================================");

        } catch (SinEnergiaException e) {
            System.out.println("\n=================================================");
            System.out.println(e.getMessage());
            System.out.println("GAME OVER. El templo es ahora tu tumba...");
            System.out.println("=================================================");
        } finally {
            sc.close();

        }

    }
}
