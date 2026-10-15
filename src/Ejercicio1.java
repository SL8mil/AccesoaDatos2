import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        File archivo = new File("enteros.dat");
        Scanner sc = new Scanner(System.in);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo))) {
            for (int i = 0; i < 6; i++) dos.writeInt(1);
            for (int i = 0; i < 10; i++) dos.writeInt(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Contenido inicial del archivo:");
        mostrarArchivo(archivo);

        int nuevoValor;
        do {
            System.out.print("\nIntroduce un número entre 0 y 9: ");
            nuevoValor = sc.nextInt();
        } while (nuevoValor < 0 || nuevoValor > 9);

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                int valor = raf.readInt();
                if (valor == 2) {
                    raf.seek(raf.getFilePointer() - 4);
                    raf.writeInt(nuevoValor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nContenido tras la modificación:");
        mostrarArchivo(archivo);
    }

    private static void mostrarArchivo(File archivo) {
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
