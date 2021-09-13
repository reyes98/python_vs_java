/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Arbol.Arbol;
import Arbol.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author steve
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public Main() {
    }

    public String buscarTratamiento(String patologia) throws IOException {
        File fichero = new File("src\\Archivo\\tratamientos.txt");              //2
        String salida = "";                                                     //2
        Scanner sc = new Scanner(System.in);                                    //3
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));    //2

            String linea = "";                                                  //2    
            boolean encontrado = false;                                         //2
            while ((linea = br.readLine()) != null) {                           //3            
                int fin = linea.indexOf(";");                                   //3
                String line = linea.substring(0, fin);                          //3
                String li = linea.substring(fin + 1);                           //4    
                if (line.equalsIgnoreCase(patologia)) {                         //1    
                    salida = li;                                                //1
                    encontrado = true;                                          //1
                }

            }

            if (!encontrado) {                                                  //1
                System.out.println("El usuario no existe");                     //2
            }

        } catch (IOException e) {

            System.out.println(e);                                              //2
        }
        return salida;                                                          //1
    }

    //20+16n
    public static void main(String[] args) {
        // TODO code application logic here
        Arbol arbol = new Arbol();//2
        Main ma = new Main();//2
        Scanner sc = new Scanner(System.in);//3
        String contador = "1";//2
        int scan = 0;//2
        Nodo [] nodos = new Nodo[21];
        nodos[0] = new Nodo(1, "Sintomas internos?");        
        nodos[1] = new Nodo(11, "Dolor de cabeza?");
        nodos[2] = new Nodo(10, "Dolor articular?");
        nodos[3] = new Nodo(111, "Tiene tos?");
        nodos[4] = new Nodo(1110, "hipertension");
        nodos[5] = new Nodo(1111, "gripe");
        nodos[6] = new Nodo(110, "Dolor en el pecho?");
        nodos[7] = new Nodo(1101, "Dificultad al respirar?");
        nodos[8] = new Nodo(11011, "asma");
        nodos[9] = new Nodo(11010, "gripe");
        nodos[10] = new Nodo(1100, "Presenta dolor abdominal?");
        nodos[11] = new Nodo(11001, "apendicitis");
        nodos[12] = new Nodo(11000, "Vision borrosa?");
        nodos[13] = new Nodo(110001, "diabetes");
        nodos[14] = new Nodo(110000, "examenes");
        nodos[15] = new Nodo(101, "Inmovilidad total?");
        nodos[16] = new Nodo(1011, "fractura");
        nodos[17] = new Nodo(1010, "esguince");
        nodos[18] = new Nodo(100, "Se presentan erosiones en la piel?");
        nodos[19] = new Nodo(1001, "alergia");
        nodos[20] = new Nodo(1000, "gripe");
        
        for (Nodo nodo : nodos) {
            arbol.addElemento(nodo);
        }
        
        Nodo nodo = arbol.buscar(Integer.parseInt(contador));
        boolean salida = false;
	
        
        while (!salida) {
            nodo = arbol.buscar(Integer.parseInt(contador));

            System.out.println(nodo.getDato());
            if (nodo.getDato().toString().contains("?")) {
                System.out.println("SI/1");
                System.out.println("NO/0");
                scan = sc.nextInt();
                if (scan == 1) {
                    contador += "1";
                } else {
                    contador += "0";
                }
            } else {
                salida = true;
                try {
                    System.out.println(ma.buscarTratamiento(nodo.getDato().toString()));
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
