package Tools;

public class Impressora {

    public static String textoVermelho = "\u001B[31m";

    public static String pararCor = "\u001B[0m";

    public static String textoVerde = "\u001B[32m";

    public static String textoAmarelo = "\u001B[33m";

    public static String textoAzul = "\u001B[36m";

    public static String cabecalho = "\t\t\t\tTIPO" + String. format("%-23s", "") + "NOME" + String. format("%-13s", "") + "PREÇO" + String. format("%-15s", "") + "CLASSES" + String. format("%-44s", "") + "OUTROS";


    /**
     * Metodo para imprimir texto letra a letra numa determinada velocidade
     * @param input Recebe uma string com o texto a imprimir
     */
    public static void imprimirTextoLento(String input) {
            try {
                for (int i = 0; i < input.length(); i++) {
                    char currentChar = input.charAt(i);
                    System.out.print(currentChar);
                    Thread.sleep(20); // Sleep for half a second (500 milliseconds)
                }
                System.out.println("\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

