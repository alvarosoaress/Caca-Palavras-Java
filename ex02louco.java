import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ex02louco {

    public static int ver(int num2) { // VERificar se o numero gerado aleatoriamente comporta o
                                      // tanto de caracteres da palavra escolhida
        Random rand = new Random();
        int num = rand.nextInt(10);
        if (num <= 10 - num2)
            return num;
        else
            return ver(num2);
    }

    public static int contem(int aux, int tamanhoVetor, ArrayList repetidos) {

        if (repetidos.contains(aux)) {
            aux = ver(tamanhoVetor);
            return contem(aux, tamanhoVetor, repetidos);
        } else {
            return aux;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        // int repetidos [][] = new int [10][10];

        int i = 0;
        int j = 0;
        int k = 0;
        int aux = 0;
        int aux3 = 0;

        ArrayList<Integer> repetidosI = new ArrayList<Integer>();
        ArrayList<Integer> repetidosJ = new ArrayList<Integer>();

        char caca[][] = new char[10][10]; // caça palavras aleatorias
        String words[] = {"cara", "lousa", "xadrez", "casa", "brabo"}; // palavras escolhidas

        for (i = 0; i < caca.length; i++) { // formando o caça palavras com palavras aleatorias em
                                            // maíusculo
            for (j = 0; j < caca[0].length; j++) {
                char c = (char) (rand.nextInt(26) + 'a');
                caca[i][j] = Character.toUpperCase(c);

            }
        }
        while (k < words.length) { // colocando as palavras escolhidas no meio das aleatorias em
                                   // posições aleatórias
            aux = contem(ver(words[k].length()), words[k].length(), repetidosI);
            aux3 = contem(ver(words[k].length()), words[k].length(), repetidosJ);
            repetidosI.add(aux);
            repetidosJ.add(aux3);

            for (j = 0; j < words[k].length(); j++) {

                caca[aux][j + aux3] = words[k].charAt(j); // colocando em posições aleatórias

            }
            k++;
        }


        for (i = 0; i < caca.length; i++) { // printando a matriz pronta

            for (j = 0; j < caca[0].length; j++) {
                System.out.printf("%c\t", caca[i][j]);
            }
            System.out.println(" ");
            // System.out.printf("-----------------------------%n");
        }

    }
}
