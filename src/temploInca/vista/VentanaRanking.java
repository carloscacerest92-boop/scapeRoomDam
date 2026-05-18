package temploInca.vista;

import temploInca.persistencia.GestorMySQL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaRanking extends JFrame {

    public VentanaRanking() {
        // configuramos la ventana
        setTitle("Ranking de Exploradores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // creamos la tabla y sus columnas
        String[] columnas = {"Nombre", "Energia", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);

        // Con los datos que nos da mySql rellenamos las filas
        GestorMySQL gestor = new GestorMySQL();
        List<String[]> datos = gestor.obtenerRanking();

        for (String[] fila : datos) {
            modelo.addRow(fila);
        }

        // añadimos la trabla para que se vean las columnas y lo metemos a la ventana
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }
}