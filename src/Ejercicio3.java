import java.io.*;
import java.util.Scanner;

public class Ejercicio3 {
    private static final int LONGITUD_TEXTO = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile("juegos.dat", "rw")) {
            raf.seek(raf.length());

            System.out.print("Cuántos juegos deseas registrar?: ");
            int numJuegos = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= numJuegos; i++) {
                System.out.println("\n--- Juego " + i + " ---");
                System.out.print("Nombre (máx 10 chars): ");
                String nombre = sc.nextLine();

                System.out.print("Edad mínima recomendada: ");
                int edad = sc.nextInt();
                sc.nextLine();

                System.out.print("Temática (máx 10 chars): ");
                String tematica = sc.nextLine();

                raf.writeInt(i);
                escribirCadenaFija(raf, nombre, LONGITUD_TEXTO);
                raf.writeInt(edad);
                escribirCadenaFija(raf, tematica, LONGITUD_TEXTO);
            }

            System.out.println("\nContenido de juegos.dat");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                int orden = raf.readInt();
                String nombre = leerCadenaFija(raf, LONGITUD_TEXTO);
                int edad = raf.readInt();
                String tematica = leerCadenaFija(raf, LONGITUD_TEXTO);

                System.out.printf("Orden: %d | Nombre: %-10s | Edad Mín: %2d | Temática: %-10s%n",
                        orden, nombre, edad, tematica);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirCadenaFija(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuffer sb = new StringBuffer(texto);
        sb.setLength(longitud);
        raf.writeChars(sb.toString());
    }

    public static String leerCadenaFija(RandomAccessFile raf, int longitud) throws IOException {
        char[] chars = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            chars[i] = raf.readChar();
        }
        return new String(chars).trim();
    }
}
