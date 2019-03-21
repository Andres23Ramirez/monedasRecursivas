/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monedas;

import com.sun.org.glassfish.gmbal.ParameterNames;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class Monedas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Menu 
        System.out.println("Ingrese la opción del metodo");
        System.out.println("1- Metodo con monedas ilimitadas");
        System.out.println("2- Metodo con monedas limitadas");
        Scanner entrada2 = new Scanner(System.in);
        int opcion = entrada2.nextInt();
        
        //Array que representa las opciones de monedas
        int[] monedas = {100, 50, 25, 10, 5, 1};
        ArrayList<Integer> respuesta = new ArrayList<Integer>();

        
        switch (opcion) {
            case 1:
                System.out.println("");
                System.out.println("Monedas sin limite");

                //Pedir al usuario el valor
                System.out.println("Ingrese el valor a devolver");
                Scanner entrada = new Scanner(System.in);
                int valor = entrada.nextInt();               
                
                //Llamado al metodo que realiza la solucion 
                devolver(valor, monedas, 0, respuesta, 0);
                break;

            case 2:
                System.out.println("");
                System.out.println("Monedas con limite");

                //Pedir al usuario el valor
                System.out.println("Ingrese el valor a devolver");
                Scanner entrada1 = new Scanner(System.in);
                int valor1 = entrada1.nextInt();

                //Pedir al usuario el limite de monedas
                System.out.println("Ingrese el limite de monedas de cada denominacion");
                Scanner entrada3 = new Scanner(System.in);
                int limite = entrada3.nextInt();

                //Llamada al metodo que realiza la solucion
                devolverConLimite(valor1, monedas, 0, respuesta, 0, limite, 0);                
                break;
                
            default:
                System.out.println("opcion no valida");
                break;
        }
    }

    /**
     * Metodo que encuentra la mejor combinacion de monedas para devolver
     * @param valor numero que representa el valor a devolver
     * @param monedas[] representa las monedas disponibles
     * @param suma valor acumalito que ayuda a encontrar la solucion
     * @param respuesta<> arrayList que almacena la combinacion de monedas que sera la respuesta
     * @param posicionMoneda valor que representa la moneda usada en el momento
     */
    private static void devolver(int valor, int[] monedas, int suma, ArrayList<Integer> respuesta, int posicionMoneda) {

        //condicion de parada que encuentra la solucion
        if (valor == suma) {
            System.out.print("Devuelve: ");
            for (Integer integer : respuesta) {
                System.out.print(" " + integer);
            }
            System.out.println("");
            System.out.println("Fin del programa");
        } else if (valor >= (suma + monedas[posicionMoneda])) {
            //realiza la llamada recursiva con la misma moneda
            respuesta.add(monedas[posicionMoneda]);
            suma = suma + monedas[posicionMoneda];
            devolver(valor, monedas, suma, respuesta, posicionMoneda);
        } else {
            //Realiza la llamada recursiva on la siguiente moneda            
            posicionMoneda += 1;
            devolver(valor, monedas, suma, respuesta, posicionMoneda);
        }
    }
    
    /**
     * Metodo que encuentra la mejor combinacion de monedas para devolver con un limite de cada moneda
     * @param valor numero que representa el valor a devolver
     * @param monedas[] representa las monedas disponibles
     * @param suma valor acumalito que ayuda a encontrar la solucion
     * @param respuesta<> arrayList que almacena la combinacion de monedas que sera la respuesta
     * @param posicionMoneda valor que representa la moneda usada en el momento
     * @param limite valor del maximo de monedas que se pueden usar
     * @param monedasUsadas acumula las monedas de cada valor que se han usado hasta el momento
     */

    private static void devolverConLimite(int valor, int[] monedas, int suma, ArrayList<Integer> respuesta, 
            int posicionMoneda, int limite, int monedasUsadas) {
        
        //condicion de para, que encuentra la solucion
        if (valor == suma || (monedasUsadas == limite && posicionMoneda == monedas.length -1  )) {
            System.out.print("Devuelve: ");
            for (Integer integer : respuesta) {
                System.out.print(" " + integer);
            }
            if((suma - valor != 0)){
                System.out.println("");
                System.out.println("La suma parcial es: "+suma);
                System.out.println("");
                System.out.println("El valor restante es: "+(valor - suma));
            }
            System.out.println("");
            System.out.println("Fin del programa");
        } else if ( ( valor >= (suma + monedas[posicionMoneda]) ) && (monedasUsadas<limite)) {
            //Realiza la llamada recursiva con la misma moneda
            respuesta.add(monedas[posicionMoneda]);
            suma = suma + monedas[posicionMoneda];
            monedasUsadas = monedasUsadas + 1;
            devolverConLimite(valor, monedas, suma, respuesta, posicionMoneda, limite, monedasUsadas);
        } else {
            //Realiza la llmada recursica con la siguiente moneda            
            posicionMoneda += 1;
            monedasUsadas = 0;
            devolverConLimite(valor, monedas, suma, respuesta, posicionMoneda, limite, monedasUsadas);
        }
    }
}
