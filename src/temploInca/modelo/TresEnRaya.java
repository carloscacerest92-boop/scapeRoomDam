package temploInca.modelo;

import java.util.Scanner;

public class TresEnRaya extends Sala {
    private char[] tablero;

    public TresEnRaya(String nombre, String descripcion, String itemRecompensa) {
        super(nombre, descripcion, itemRecompensa);
        this.tablero = new char[9];
    }

    @Override
    public boolean resolver(Jugador jugador, Scanner sc) {
        mostrarHistoria();
        System.out.println("Amaru: \"Para avanzar, debes vencerme o empatarme en Tres en Raya.\"");

        while (jugador.estaVivo() && this.estado != EstadoSala.COMPLETADA) {

            for (int i = 0; i < 9; i++) tablero[i] = ' ';

            int resultado = jugarPartida(sc);

            if (resultado == 1 || resultado == 2) {
                System.out.println("✨ ¡Has superado el desafío de Amaru!");
                if (this.itemRecompensa != null) {
                    jugador.recogerItem(this.itemRecompensa);
                }
                this.setEstado(EstadoSala.COMPLETADA);
                return true;
            } else {
                System.out.println("Amaru te ha derrotado...");
                jugador.gastarEnergia(3); 
                if (jugador.estaVivo()) {
                    System.out.println("Amaru: \"Vuelve a intentarlo, mortal...\"");
                }
            }
        }
        return false;
    }

    private int jugarPartida(Scanner sc) {
        boolean turnoJugador = true;
        System.out.println("Tu ficha es X. Amaru O.");

        while (true) {
            mostrarTablero();

            if (turnoJugador) {
                int pos = -1;
                while (true) {
                    System.out.print("Elige posición (1-9): ");
                    String entrada = sc.nextLine();

                    try { 
                        pos = Integer.parseInt(entrada) - 1;
                        if (pos >= 0 && pos <= 8 && tablero[pos] == ' ') break;
                        else System.out.println("Posición inválida u ocupada.");
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido, no letras.");
                    }
                }
                tablero[pos] = 'X';
                if (hayGanador('X')) { mostrarTablero(); return 1; }
            } else {
                int pos;
                do { pos = (int) (Math.random() * 9); } while (tablero[pos] != ' ');
                tablero[pos] = 'O';
                System.out.println("Amaru elige la posición " + (pos + 1));
                if (hayGanador('O')) { mostrarTablero(); return 0; }
            }

            boolean lleno = true;
            for (char c : tablero) if (c == ' ') lleno = false;
            if (lleno) { mostrarTablero(); return 2; }

            turnoJugador = !turnoJugador;
        }
    }

    private void mostrarTablero() {
        System.out.println(" 1   2   3");        
        System.out.println("\n " + tablero[0] + " | " + tablero[1] + " | " + tablero[2]);        
        System.out.println("---+---+---");
        System.out.println(" " + tablero[3] + " | " + tablero[4] + " | " + tablero[5]);
        System.out.println(" 4   5   6");
        System.out.println("---+---+---");        
        System.out.println(" " + tablero[6] + " | " + tablero[7] + " | " + tablero[8] + "\n");
        System.out.println(" 7   8   9");
    }

    private boolean hayGanador(char f) {
        int[][] lineas = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] l : lineas) {
            if (tablero[l[0]] == f && tablero[l[1]] == f && tablero[l[2]] == f) return true;
        }
        return false;
    }

}
