import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File archivo = new File("enteros.dat");

        int numero;
        do {
            System.out.print("Introduce un número entre 0 y 9: ");
            numero = sc.nextInt();
        } while (numero < 0 || numero > 9);

        System.out.print("¿Cuántas veces deseas añadirlo?: ");
        int cantidad = sc.nextInt();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo, true))) {
            for (int i = 0; i < cantidad; i++) {
                dos.writeInt(numero);
            }
            System.out.println("Datos añadidos correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nContenido actualizado de enteros.dat:");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivo))) {
            while (dis.available() > 0) {
                System.out.print(dis.readInt() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}