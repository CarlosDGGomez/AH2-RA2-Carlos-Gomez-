package serpientesyescaleras;

import java.util.Scanner;

public class Serpientesyescaleras {
    int f = 8;
    int c = 8;
    boolean inversa = false;
    boolean gameOver = false;
    boolean win = false;
    int array[][] = new int[f][c];
    String arrayPenalizacion[][] = new String[f][c];
    int contador = 0;

    String digitos = "";
    int posicionjugador = 1;
    int posicionpenalizacion = 56;
    Scanner entrada = new Scanner(System.in);
    Scanner entradaInt = new Scanner(System.in);
    int randomP = (int) (Math.random() * (7 - 0 + 1) + 0);
    int randomCPorFila = (int) (Math.random() * (3 - 1 + 1) + 1);
    int dados = 0;

    public static void main(String[] args) {
        Serpientesyescaleras clase = new Serpientesyescaleras();
        Scanner in = new Scanner(System.in);
        boolean salir = true;
        boolean sesion = false;
        int op = 0;

        while (salir) {
            System.out.println("\n===Menu Principal==\n"
                    + "1. Juego nuevo\n"
                    + "2. Volver al juego\n"
                    + "3. Salir\n"
                    + "====================");
            System.out.println("ingresa la opcion");
            op = in.nextInt();
            switch (op) {
                case 1:
                    clase.rellenarArrays();
                    clase.jugar();
                    sesion = true;
                    break;
                case 2:
                    if (sesion) {
                        clase.jugar();
                    } else {
                        clase.rellenarArrays();
                        clase.jugar();
                        sesion = true;
                    }
                    break;
                case 3:
                    salir = false;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    private void rellenarArrays() {
        contador = 0;
        posicionjugador = 1;
        //NUMEROS
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                contador++;
                array[i][j] = contador;
            }
        }
        //ESPACIOS
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                arrayPenalizacion[i][j] = "";
            }
        }
        // LENARPenal
        for (int i = 0; i < f; i++) {
            randomCPorFila = (int) (Math.random() * (3 - 1 + 1) + 1);

            for (int j = 0; j < randomCPorFila; j++) {
                randomP = (int) (Math.random() * (7 - 0 + 1) + 0);
                arrayPenalizacion[i][randomP] = "#";
            }
        }
    }
    private void jugar() {
        while (true) {
            System.out.println("\nTablero");
            System.out.println("---------------------------------------------------------------------------------");

            for (int i = f - 1; i <= f && i != -1; i--) {
                if (inversa) {
                    //Sup1
                    for (int j = c - 1; j <= c && j != -1; j--) {
                        digitos = Integer.toString(array[i][j]);
                        if (digitos.length() == 1) {
                            System.out.print("│      " + array[i][j]);
                        } else {
                            System.out.print("│      " + array[i][j]);
                        }
                    }
                    //inf1
                    System.out.println("│       ");
                    for (int j = c - 1; j <= c && j != -1; j--) {
                        if (array[i][j] == posicionjugador) {
                            System.out.print("│     @" + arrayPenalizacion[i][j]);
                            if (arrayPenalizacion[i][j].equals("#")) {
                                gameOver = true;
                            }
                        } else {
                            System.out.print("│       " + arrayPenalizacion[i][j]);
                        }
                    }
                    inversa = false;
                } else {
                    //Super2
                    for (int j = 0; j < c; j++) {
                        digitos = Integer.toString(array[i][j]);
                        if (digitos.length() == 1) {
                            System.out.print("│      " + array[i][j]);
                        } else {
                            System.out.print("│      " + array[i][j]);
                        }
                    }
                    //Inf2
                    System.out.println("│        ");
                    for (int j = 0; j < c; j++) {
                        if (array[i][j] == posicionjugador) {
                            System.out.print("│      @" + arrayPenalizacion[i][j]);
                            if (arrayPenalizacion[i][j].equals("@")) {
                                gameOver = true;
                            }
                        } else {
                            System.out.print("│        " + arrayPenalizacion[i][j]);
                        }
                    }
                    inversa = true;
                }
                System.out.println("│           ");
                System.out.println("---------------------------------------------------------------------------------");
            }
            if (posicionjugador > 64) {
                win = true;
            }
            System.out.println("Has avanzado " + dados + "casillas\n");
            if (gameOver) {
                System.out.println("Caiste en una penalizacion\n");
                gameOver = false;
            }
            if (win) {
                System.out.println("Has ganado\n");
            }
            //Menu
            System.out.println("Tira el dado con la tecla r y sale del juego con la techa p");
            String respuesta = entradaInt.nextLine();
            if (respuesta.equals("r")) {
                dados = (int) (Math.random() * (6 - 2 + 1) + 2);
                posicionjugador += dados;
            } else if (respuesta.equals("p")) {
                break;
            } else {
                System.out.println("\n Salir?");
                String salir = entrada.nextLine();
                if (salir.equals("s")) {
                    break;
                }
            }
        }
    }
}
