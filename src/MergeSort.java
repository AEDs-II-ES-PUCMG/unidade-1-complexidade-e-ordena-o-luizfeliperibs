import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<T extends Comparable<T>> implements IOrdenador<T>{
    
        private long comparacoes;
        private long movimentacoes;
        private long inicio;
        private long termino;
        private T[] dadosOrdenados;
        private Comparator<T> comparador;
        
        public MergeSort() {
            comparacoes = 0;
            movimentacoes = 0;
        }
         
        @Override
        public T[] ordenar(T[] dados) {    
            return ordenar(dados, T::compareTo);
        }

        @Override
        public T[] ordenar(T[] dados, Comparator<T> comparador) {    
            this.comparador = comparador;
            int tamanho = dados.length;
            dadosOrdenados = Arrays.copyOf(dados, tamanho);
            inicio = System.nanoTime();
            mergesort(0, tamanho-1);
            termino = System.nanoTime();
            return dadosOrdenados;
        }

        public double getTempoOrdenacao() {
           return termino - inicio;
        }

        public double getTempoMs() {
           return (termino - inicio) / 1_000_000.0;
        }
    
        private T[] mergesort(int ini, int fim){
            if(ini < fim){
                int meio = (fim+ini)/2;
                mergesort(ini, meio );
                mergesort(meio+1, fim);
                dadosOrdenados = merge(ini, fim, dadosOrdenados); 
            }
            return dadosOrdenados;
        }

        private T[] merge(int inicio, int fim, T[] dados){
            T[] novo = Arrays.copyOf(dados, dados.length);
            int meio = (inicio+fim)/2;
            int indice1 = inicio;
            int indice2 = meio+1;
            int pos = inicio;
            while(indice1 <= meio && indice2 <= fim){
                comparacoes++;
                
                if(this.comparador.compare(dados[indice1],dados[indice2]) <=0)
                    novo[pos] = dados[indice1++];
                else
                    novo[pos] = dados[indice2++];
                
                pos++;
                movimentacoes++;
            }
            int origem = indice1;
            int destino = meio;
           
            if(indice1 > meio){
                origem = indice2;
                destino = fim;
            }
           
            for(int i = origem; i<=destino; i++){
                novo[pos++] = dados[i];
                movimentacoes++;
            }
            return novo;
        }        
 
        public long getComparacoes() {
            return comparacoes;
        }
        
        public long getMovimentacoes() {
            return movimentacoes;
        }
        
}

