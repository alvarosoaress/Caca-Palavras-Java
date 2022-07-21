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
        Random rand1 = new Random();
        int num = rand1.nextInt(10);
        if (num <= 10 - num2) {
            return num;
        } else {
            return ver(num2); // Caso não comporte, a função irá se chamar novamente

        }
    }

    // Verificando se o numero para o indice I e indice J já foram sorteados
    // anteriormente, para evitar uma palavra sobrepor a outra na matriz final
    public static int contem(int aux, int tamanhoVetor, ArrayList repetidos, int uso) {
        Random random = new Random();
        if (repetidos.contains(aux)) { // usando .contains no arraylist dos indices
            if (uso == 1) {
                aux = ver(tamanhoVetor);
                return contem(aux, tamanhoVetor, repetidos, uso);
            } // Caso já tenha sorteado, irá sortear
            // outra, até vir uma nova}
            else {
                aux = random.nextInt(89);
                return contem(aux, tamanhoVetor, repetidos, uso);
            }
        } else {
            return aux;
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int i = 0, j = 0, k = 0, aux = 0, aux2 = 0, acerto = 0, chances = 3, numPalavras = 5;

        // Lista de array para estocar os numeros já sorteados para I
        ArrayList<Integer> repetidosI = new ArrayList<Integer>();

        // Lista de array para estocar os numeros já sorteados para J
        ArrayList<Integer> repetidosJ = new ArrayList<Integer>();

        ArrayList<Integer> repetidosPosicao = new ArrayList<>();

        String palavras[] = new String[5];

        String resposta;

        char caca[][] = new char[10][10]; // caça palavras aleatorias
        String words[] = {"cara", "lousa", "xadrez", "casa", "mal", "ver", "ego", "dar", "bom",
                "luz", "com", "uma", "dor", "dia", "sol", "fim", "ler", "rua", "lei", "sim", "amor",
                "fato", "mito", "caos", "vida", "auge", "medo", "alem", "rude", "mais", "amar",
                "onde", "doce", "arte", "termo", "nobre", "sutil", "moral", "muito", "honra",
                "anexo", "sobre", "sonho", "amigo", "tempo", "dever", "digno", "mundo", "exceto",
                "utopia", "casual", "idiota", "pressa", "legado", "gentil", "safado", "otario",
                "larica", "formal", "eficaz", "objeto", "acesso", "desejo", "buscar", "remoto",
                "comico", "insano", "empatia", "cultura", "refutar", "virtude", "trivial",
                "cordial", "aspecto", "mitigar", "sucesso", "alegria", "orgulho", "sensato",
                "excesso", "coragem", "modesto", "demanda", "amizade", "emotivo", "piedade",
                "intenso", "ansioso", "colapso"}; // palavras escolhidas

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
        while (k < numPalavras) {
            int posicao = contem(rand.nextInt(89), 89, repetidosPosicao, 0);
            repetidosPosicao.add(posicao);
            // atribuindo um valor aleátorio e, ao mesmo tempo, verificando se esse valor
            // já foi sorteado anteriormente

            aux = contem(ver(words[posicao].length()), words[posicao].length(), repetidosI, 1);
            aux2 = contem(ver(words[posicao].length()), words[posicao].length(), repetidosJ, 1);
            repetidosI.add(aux);
            repetidosJ.add(aux2);

            for (j = 0; j < words[posicao].length(); j++) {
                caca[aux][j + aux2] = words[posicao].charAt(j); // colocando em posições aleatórias
                // adicionando o "J+" ao valor do indice J para a palavrar ser impressa cada
                // caracter em cada posição de J horizontalmente
                palavras[k] = (words[posicao]);
            }
            k++;
        }
        while (acerto != numPalavras) {
            if(chances <= 0){
                clearConsole();
                System.out.println("Você Perdeu !");
                break;
            }


        for (i = 0; i < caca.length; i++) { // printando a matriz pronta
            System.out.printf("{%d}\t", i + 1); // imprimindo o os números das linhas
            for (j = 0; j < caca[0].length; j++) {
                System.out.printf(" %c\t", caca[i][j]); // imprimindo o conteúdo final da matriz
            }
            System.out.println(" ");
        }
        try {
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
                System.out.println("Chances restantes: "+chances);
                System.out.println(Arrays.toString(palavras));
                System.out.println("-----------------------------------------");
                System.out.println("Digite a palavra que você encontrar: ");
                resposta = input.nextLine();

                int indiceJ = repetidosJ.get(Arrays.asList(palavras).indexOf(resposta));

                for (j = indiceJ; j < (indiceJ + words[Arrays.asList(palavras).indexOf(resposta)].length()); j++) {
                    caca[repetidosI.get(Arrays.asList(palavras).indexOf(resposta))][j] = '*';
                }

                clearConsole();
                acerto++;
            } catch (IndexOutOfBoundsException nException) {
                System.out.println("Palavra nao se encontra no Caça-Palavras !");
                chances--;
                for(i=0;i<40;i++){
                    System.out.print(".");
                    Thread.sleep(50);
                }
                clearConsole();
            }
        }

    }
}

//BUGS : 1 NUMERO ALEATORIO AS VEZES DA STACK OVERFLOW
//       2 AS VEZES O SCAN N RECONHESE A PALAVRA MSM ELA ESTANDO NA MATRIZ E TIRA 1 CHANCE, PORÉM, ELA É "BORRADA" DA MATRIZ
//       3 OS "*" NÃO ESTÃO COBRINDO A PALAVRA INTEIRA