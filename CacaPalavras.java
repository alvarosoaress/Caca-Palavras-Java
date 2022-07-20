package CacaPalavras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;



public class CacaPalavras {

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
        } catch (final Exception e) {
            // Handle any exceptions.
        }
    }

    public static int ver(int num2) { // VERificar se o numero gerado aleatoriamente comporta o
        // tanto de caracteres da palavra escolhida
        Random rand = new Random();
        int num = rand.nextInt(10);
        if (num <= 10 - num2) {
            return num;
        } else {
            return ver(num2); // Caso não comporte, a função irá se chamar novamente

        }
    }

    // Verificando se o numero para o indice I e indice J já foram sorteados
    // anteriormente, para evitar uma palavra sobrepor a outra na matriz final
    public static int contem(int aux, int tamanhoVetor, ArrayList repetidos) {

        if (repetidos.contains(aux)) { // usando .contains no arraylist dos indices
            aux = ver(tamanhoVetor);
            return contem(aux, tamanhoVetor, repetidos); // Caso já tenha sorteado, irá sortear
            // outra, até vir uma nova
        } else {
            return aux;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int i = 0;
        int j = 0;
        int k = 0;
        int aux = 0;
        int aux2 = 0;
        int acerto = 0;

        // Lista de array para estocar os numeros já sorteados para I
        ArrayList<Integer> repetidosI = new ArrayList<Integer>();

        // Lista de array para estocar os numeros já sorteados para J
        ArrayList<Integer> repetidosJ = new ArrayList<Integer>();

        String palavras[] = new String[5];

        String resposta;

        char caca[][] = new char[10][10]; // caça palavras aleatorias
        String words[] = {"cara", "lousa", "xadrez", "casa", "brabo"}; // palavras escolhidas

        // formando o caça palavras com palavras aleatorias maíusculo
        for (i = 0; i < caca.length; i++) {
            for (j = 0; j < caca[0].length; j++) {
                //
                char c = (char) (rand.nextInt(26) + 'a');
                caca[i][j] = Character.toUpperCase(c);

            }
        }

        // colocando as palavras escolhidas no meio das aleatorias em posições
        // aleatórias
        while (k < words.length) {

            // atribuindo um valor aleátorio e, ao mesmo tempo, verificando se esse valor
            // já foi sorteado anteriormente
            aux = contem(ver(words[k].length()), words[k].length(), repetidosI);
            aux2 = contem(ver(words[k].length()), words[k].length(), repetidosJ);
            repetidosI.add(aux);
            repetidosJ.add(aux2);

            for (j = 0; j < words[k].length(); j++) {
                caca[aux][j + aux2] = words[k].charAt(j); // colocando em posições aleatórias
                // adicionando o "J+" ao valor do indice J para a palavrar ser impressa cada
                // caracter em cada posição de J horizontalmente
                palavras[k] = (words[k]);
            }
            k++;
        }

        boolean executado = false; // verificação se já foi executado X parte do código

        // for para imprimir os numeros de colunas, imprimir o J "em cima"
        for (j = 0; j < caca[0].length; j++) {
            if (!executado) {
                // espaço atras da primeira coluna para a formatação ficar certa
                System.out.printf("\t");
                // transformando executado em true para não ter esse espaço mais de uma vez
                executado = true;
            }
            System.out.printf("{%d}\t", j + 1); // imprimindo os numeros das colunas
        }

        System.out.println(" "); // espaço entre os numeros e a matriz

        for (i = 0; i < caca.length; i++) { // printando a matriz pronta
            System.out.printf("{%d}\t", i + 1); // imprimindo o os números das linhas
            for (j = 0; j < caca[0].length; j++) {
                System.out.printf(" %c\t", caca[i][j]); // imprimindo o conteúdo final da matriz
            }
            System.out.println(" ");
        }

        while (acerto != words.length) {
            try {
                resposta = input.nextLine();
                // "cara", "lousa", "xadrez", "casa", "brabo"

                int coe = repetidosJ.get(Arrays.asList(palavras).indexOf(resposta));
                for (j = coe; j < (coe + words[Arrays.asList(palavras).indexOf(resposta)].length()); j++) {
                    caca[repetidosI.get(Arrays.asList(palavras).indexOf(resposta))][j] = '*';
                }

                clearConsole();

                for (i = 0; i < caca.length; i++) { // printando a matriz pronta
                    System.out.printf("{%d}\t", i + 1); // imprimindo o os números das linhas
                    for (j = 0; j < caca[0].length; j++) {
                        System.out.printf(" %c\t", caca[i][j]); // imprimindo o conteúdo final da
                                                                // matriz
                    }
                    System.out.println(" ");
                }
                acerto++;
            } catch (IndexOutOfBoundsException nException) {
                System.out.println("Fora do Index");
            }
        }


    }
}
