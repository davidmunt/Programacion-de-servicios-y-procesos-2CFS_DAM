package pmiremJav;

//import java.util.*;
import java.io.*;

public class LanzadorContVocales {
	//control + shift + o
	
    public void lanzar(String vocal, String ficheroEntrada, String ficheroResultado) {
        String clase = "pmiremJav.ContadorVocales";
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(
                "java", "-cp", "bin", clase, vocal, ficheroEntrada, ficheroResultado
            );
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process p = pb.start(); 
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //leer el resultado de cada fichero
    public static int leerResultado(String fichero, int posicion) throws NumberFormatException, IOException {
    	int countVocal = 0;
        String[] vocales = {"a", "e", "i", "o", "u"};
        BufferedReader lectorNum = new BufferedReader(new FileReader(fichero));
        //leo solo la primera linea del archivo
        countVocal = Integer.parseInt(lectorNum.readLine().trim());
        System.out.println("La vocal '"+ vocales[posicion] + "' se repite " + countVocal + " veces");
        lectorNum.close();
        return countVocal;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
    	//para guardar las letras en un archivo:
    	String texto = "afbcoisdncoajcammaeuei";
    	FileWriter archivo = new FileWriter("letras.txt");
        archivo.write(texto);  //introducimos el texto en el archivo
        archivo.close(); //cerramos
    	LanzadorContVocales l = new LanzadorContVocales();
        //array con los ficheros donde se almacenan los resultados
        String[] ficherosResultados = {"resultado_a.txt", "resultado_e.txt", "resultado_i.txt", "resultado_o.txt", "resultado_u.txt"};
        // Lanzar los procesos para contar cada vocal
        l.lanzar("a", "letras.txt", ficherosResultados[0]);
        l.lanzar("e", "letras.txt", ficherosResultados[1]);
        l.lanzar("i", "letras.txt", ficherosResultados[2]);
        l.lanzar("o", "letras.txt", ficherosResultados[3]);
        l.lanzar("u", "letras.txt", ficherosResultados[4]);
        //esperar a que los procesos terminen
        //Thread.sleep(5000);
        //sumar el total de vocales
        int totalVocales = 0;
        for (int i = 0; i < ficherosResultados.length; i++) {
        	totalVocales += leerResultado(ficherosResultados[i], i);
		}
        System.out.println("El numero total de vocales es: " + totalVocales);
    }
}
