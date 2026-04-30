package temploInca;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class Jugador {

    private String nombre;
    private int energia;
    private Sala salaActual;

    private Set<String> inventario;
    private Map<String, String> diarioExploracion;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.energia = 20;
        this.inventario = new HashSet<>();
        this.diarioExploracion = new HashMap<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }

    public Sala getSalaActual() { return salaActual; }

    public void setSalaActual(Sala salaActual) {
        this.salaActual = salaActual;
        this.diarioExploracion.put(salaActual.getNombre(), "Visitada con " + this.energia + " de energia.");
    }

    public Set<String> getInventario() { return inventario; }
    public void limpiarInventario() { this.inventario.clear(); }

    public boolean estaVivo() {
        return this.energia > 0;
    }

    public void gastarEnergia(int cantidad) {
        this.energia -= cantidad;
        if(this.energia <= 0) {
            this.energia = 0;
            throw new SinEnergiaException("El explorador " + this.nombre + " ha colapsado. Sin energia vital.");
        }
        System.out.println(this.nombre + " pierde energia. (te quedan: " + this.energia + ")");
    }

    public void recogerItem(String item) {
        if (this.inventario.add(item)) {
            System.out.println("Has recogido: [" + item + "]");
        }
    }

    public void recogerItemSilencioso(String item) {
        this.inventario.add(item);
    }

    public void mostrarInventario() {
        System.out.println("\n--- INVENTARIO ---");
        if(this.inventario.isEmpty()) {
            System.out.println("[VACIO]");
        } else {
            for (String item : this.inventario) {
                System.out.println("-- " + item);
            }
        }
        System.out.println("------------------\n");
    }

    public void mostrarReliquiasClave() {
        System.out.println("\n--- ANALISIS DE RELIQUIAS (STREAMS) ---");
        List<String> reliquias = this.inventario.stream()
                .filter(item -> item.matches(".*\\d.*"))
                .collect(Collectors.toList());

        long cantidad = reliquias.stream().count();

        System.out.println("Tienes " + cantidad + " reliquias sagradas listas para usar.");
        reliquias.forEach(r -> System.out.println("- " + r));
        System.out.println("---------------------------------------\n");
    }

    public void leerDiario() {
        System.out.println("\n--- DIARIO DE EXPLORACION (MAPA) ---");
        for (Map.Entry<String, String> entrada : this.diarioExploracion.entrySet()) {
            System.out.println("> " + entrada.getKey() + " -> " + entrada.getValue());
        }
    }

    public boolean tieneItem(String item) {
        return this.inventario.contains(item);
    }

    public void usarItem(String item) throws ItemNoEncontradoException {
        if (!this.inventario.contains(item)) {
            throw new ItemNoEncontradoException("Error critico: No posees el item [" + item + "] en tu mochila.");
        }
        System.out.println("Has examinado el item: " + item + ". Sientes su poder antiguo.");
    }
}