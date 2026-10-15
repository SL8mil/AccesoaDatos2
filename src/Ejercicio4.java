import java.io.*;
import java.util.Scanner;

public class Ejercicio4 {
    private static final int LONGITUD_TEXTO = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la edad del jugador: ");
        int edadBuscar = sc.nextInt();

        File archivo = new File("juegos.dat");
        if (!archivo.exists()) {
            System.out.println("El archivo juegos.dat no existe. Ejecuta el Ejercicio 3 primero.");
            return;
        }

        System.out.println("\nJuegos recomendados para edad <= " + edadBuscar);
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            boolean encontrado = false;

            while (raf.getFilePointer() < raf.length()) {
                raf.readInt();
                String nombre = Ejercicio3.leerCadenaFija(raf, LONGITUD_TEXTO);
                int edadMinima = raf.readInt();
                String tematica = Ejercicio3.leerCadenaFija(raf, LONGITUD_TEXTO);

                if (edadMinima <= edadBuscar) {
                    System.out.printf("Nombre: %-10s | Temática: %-10s (Edad mín: %d)%n",
                            nombre, tematica, edadMinima);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron juegos adecuados para esta edad.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
