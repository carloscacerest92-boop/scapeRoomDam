package temploInca;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Sala implements Reto {
    // Atributos protegidos para que lo shijos puedan usarlos
    protected String nombre;
    protected String descripcion;
    protected String itemRecompensa;
    protected  EstadoSala estado;
    protected ArrayList<Sala> vecinas;

    public Sala(String nombre, String descripcion, String itemRecompensa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.itemRecompensa = itemRecompensa;
        this.estado = EstadoSala.ACCESIBLE;
        this.vecinas = new ArrayList<>();
    }

    public void agregarVecina(Sala vecina){
        this.vecinas.add(vecina);
    }

    public ArrayList<Sala> getVecinas() {
        return vecinas;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoSala getEstado() {
        return estado;
    }

    public void setEstado(EstadoSala estado) {
        this.estado = estado;
    }

    public void mostrarHistoria(){
        System.out.println("\n======================================================");
        System.out.println(this.nombre.toUpperCase());
        System.out.println("========================================================");
        System.out.println(this.descripcion);
        System.out.println("--------------------------------------------------------");
    }

    @Override
    public abstract boolean resolver(Jugador jugador, Scanner sc);
}
