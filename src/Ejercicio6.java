import java.io.*;

public class Ejercicio6 {
    public static final double PRECIO_CLASE = 45.7;

    public static void main(String[] args) {
        File fPracticas = new File("practicas.dat");
        if (!fPracticas.exists()) {
            System.out.println("No existe practicas.dat. Ejecuta el Ejercicio 5 primero.");
            return;
        }

        try (RandomAccessFile rafIn = new RandomAccessFile(fPracticas, "r");
             RandomAccessFile rafOut = new RandomAccessFile("precio.dat", "rw")) {

            rafOut.setLength(0);

            while (rafIn.getFilePointer() < rafIn.length()) {
                String nombre = Ejercicio3.leerCadenaFija(rafIn, Ejercicio5.TAM_NOMBRE);
                Ejercicio3.leerCadenaFija(rafIn, Ejercicio5.TAM_APELLIDOS);
                String dni = Ejercicio3.leerCadenaFija(rafIn, Ejercicio5.TAM_DNI);
                rafIn.readInt();
                int clases = rafIn.readInt();

                double totalPagar = clases * PRECIO_CLASE;

                Ejercicio3.escribirCadenaFija(rafOut, nombre, Ejercicio5.TAM_NOMBRE);
                Ejercicio3.escribirCadenaFija(rafOut, dni, Ejercicio5.TAM_DNI);
                rafOut.writeInt(clases);
                rafOut.writeDouble(totalPagar);
            }

            System.out.println("Fichero 'precio.dat' generado con éxito.");

            System.out.println("\n=== CONTENIDO DE PRECIO.DAT ===");
            rafOut.seek(0);
            while (rafOut.getFilePointer() < rafOut.length()) {
                String nombre = Ejercicio3.leerCadenaFija(rafOut, Ejercicio5.TAM_NOMBRE);
                String dni = Ejercicio3.leerCadenaFija(rafOut, Ejercicio5.TAM_DNI);
                int clases = rafOut.readInt();
                double total = rafOut.readDouble();

                System.out.printf("Nombre: %-10s | DNI: %-9s | Horas/Clases: %2d | Total: %.2f€%n",
                        nombre, dni, clases, total);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
