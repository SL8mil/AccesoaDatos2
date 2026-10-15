import java.io.*;
import java.util.Scanner;

public class Ejercicio5 {
    public static final int TAM_NOMBRE = 10;
    public static final int TAM_APELLIDOS = 35;
    public static final int TAM_DNI = 9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cuántos alumnos deseas registrar?: ");
        int numAlumnos = sc.nextInt();
        sc.nextLine();

        try (RandomAccessFile raf = new RandomAccessFile("practicas.dat", "rw")) {
            raf.seek(raf.length());

            for (int i = 0; i < numAlumnos; i++) {
                System.out.println("\nAlumno " + (i + 1));
                System.out.print("Nombre (máx 10 chars): ");
                String nombre = sc.nextLine();

                System.out.print("Apellidos (máx 35 chars): ");
                String apellidos = sc.nextLine();

                System.out.print("DNI (8 dígitos + 1 letra): ");
                String dni = sc.nextLine();

                System.out.print("Vehículo de prácticas (2 dígitos): ");
                int vehiculo = sc.nextInt();

                System.out.print("Número de clases de prácticas: ");
                int numClases = sc.nextInt();
                sc.nextLine();

                Ejercicio3.escribirCadenaFija(raf, nombre, TAM_NOMBRE);
                Ejercicio3.escribirCadenaFija(raf, apellidos, TAM_APELLIDOS);
                Ejercicio3.escribirCadenaFija(raf, dni, TAM_DNI);
                raf.writeInt(vehiculo);
                raf.writeInt(numClases);
            }

            System.out.println("\nListado de Alumnos");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                String nombre = Ejercicio3.leerCadenaFija(raf, TAM_NOMBRE);
                String apellidos = Ejercicio3.leerCadenaFija(raf, TAM_APELLIDOS);
                String dni = Ejercicio3.leerCadenaFija(raf, TAM_DNI);
                int vehiculo = raf.readInt();
                int clases = raf.readInt();

                System.out.printf("Nombre: %-10s | Apellidos: %-35s | DNI: %-9s | Vehículo: %02d | Clases: %02d%n",
                        nombre, apellidos, dni, vehiculo, clases);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}