package pmiremJav;

import java.io.*;
//import java.nio.file.*;
//import java.util.*;

public class ContadorVocales {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Necesitas introducir 3 variables al llamar a esta clase");
            return;
        }
        //guardamos en las variables la informacion de la letra a contar, fichero de entrada y fichero don de se va a guardar el count de la letra en concreto
        String vocal = args[0];
        String ficheroEntrada = args[1];
        String ficheroSalida = args[2];
        
        BufferedReader lector = new BufferedReader(new FileReader(ficheroEntrada));
        //leo solo la primera linea del archivo
        String texto = lector.readLine();
        
        //contamos el numero de veces que se repite la letra
        int contadorVoc = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == vocal.charAt(0)) {
            	contadorVoc++;
            }
        }
        lector.close();
        
        //introducimos al fichero el resultado del contador
    	FileWriter archivo = new FileWriter(ficheroSalida);
    	archivo.write(Integer.toString(contadorVoc));   //lo convierto a int por que si no el numero me lo inserta en ASCII 
        archivo.close();
    }
}
