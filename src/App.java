import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random();
    
    static Integer[] gerarVetorObjetos(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, 10 * tamanho);
        }
        return vetor;
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcaoAlgoritmo, opcaoTamanho;

        do {
            System.out.println("\n--- MENU DE ORDENAÇÃO ---");
            System.out.println("1. BubbleSort");
            System.out.println("2. InsertionSort");
            System.out.println("3. SelectionSort");
            System.out.println("4. MergeSort");
            System.out.println("0. Sair");
            System.out.print("Escolha o algoritmo: ");
            opcaoAlgoritmo = leitor.nextInt();

            if (opcaoAlgoritmo == 0) break;

            System.out.println("\n--- ESCOLHA O TAMANHO DOS TESTES ---");
            System.out.println("1. Pequeno (3 a 48)");
            System.out.println("2. Médio (12.500 a 200.000)");
            System.out.println("3. Grande (31M a 500M) - CUIDADO: Requer muita RAM");
            System.out.print("Escolha o tamanho: ");
            opcaoTamanho = leitor.nextInt();

            int[] tamanhosEscolhidos;
            switch (opcaoTamanho) {
                case 1 -> tamanhosEscolhidos = tamanhosTestePequeno;
                case 2 -> tamanhosEscolhidos = tamanhosTesteMedio;
                case 3 -> tamanhosEscolhidos = tamanhosTesteGrande;
                default -> {
                    System.out.println("Tamanho inválido.");
                    continue;
                }
            }

            executarTestes(opcaoAlgoritmo, tamanhosEscolhidos);

        } while (opcaoAlgoritmo != 0);

        leitor.close();
        System.out.println("Programa encerrado.");
    }

    static void executarTestes(int algoritmo, int[] tamanhos) {
        for (int tam : tamanhos) {
            System.out.println("\n-----------------------------------------");
            System.out.println("Teste com vetor de tamanho: " + tam);
            Integer[] vetor = gerarVetorObjetos(tam);
            IOrdenador<Integer> ordenador = null;
            String nome = "";

            switch (algoritmo) {
                case 1 -> { ordenador = new BubbleSort<>(); nome = "BubbleSort"; }
                case 2 -> { ordenador = new InsertionSort<>(); nome = "InsertionSort"; }
                case 3 -> { ordenador = new SelectionSort<>(); nome = "SelectionSort"; }
                case 4 -> { ordenador = new MergeSort<>(); nome = "MergeSort"; }
            }

            if (ordenador != null) {
                ordenador.ordenar(vetor);
                System.out.println("Método: " + nome);
                System.out.println("Comparações: " + ordenador.getComparacoes());
                System.out.println("Movimentações: " + ordenador.getMovimentacoes());
                System.out.printf("Tempo de ordenação: %.4f ms\n", ordenador.getTempoOrdenacao());
            }
        }
    }
}