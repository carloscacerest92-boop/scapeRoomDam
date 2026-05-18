package temploInca.persistencia;

import temploInca.modelo.Jugador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class GestorFicheros {

    private static final String ARCHIVO_GUARDADO = "partida_guardada.dat";

    public void guardarPartida(Jugador jugador) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_GUARDADO))) {
            oos.writeObject(jugador);
            System.out.println("Partida guardada correctamente. Puedes cerrar el juego.");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    public Jugador cargarPartida() {
        File archivo = new File(ARCHIVO_GUARDADO);
        if (!archivo.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Jugador jugadorCargado = (Jugador) ois.readObject();
            System.out.println("Partida recuperada. Bienvenido de nuevo, " + jugadorCargado.getNombre());
            return jugadorCargado;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }
}