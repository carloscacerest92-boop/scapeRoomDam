package temploInca;

import java.util.ArrayList;

public class Jugador {

    //atributos
    private String nombre;
    private int energia;
    private ArrayList<String> inventario;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.energia = 6; // empezamos con 6
        this.inventario = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public boolean estaVivo(){
        return this.energia > 0;
    }

    public void gastarEnergia(int cantidad){
        this.energia -= cantidad;
        if(this.energia<=0){
            this.energia = 0;
            throw new SinEnergiaException(this.nombre+" ha colapsado!! te quedaste sin energia vital!!!");
        }
        System.out.println(this.nombre + " pierde energia. (te quedan: " + this.energia+")");
    }

    public void recogerItem(String item){
        this.inventario.add(item);
        System.out.println("Has recogido: ["+item+"]");
    }

    public void mostrarInventario(){
        System.out.println("\n--- INVENTARIO ---");
        if(this.inventario.isEmpty()) System.out.println("[VACIO]");
        else for (String item : this.inventario) System.out.println("--"+item);
        System.out.println("------------------\n");
    }
}
