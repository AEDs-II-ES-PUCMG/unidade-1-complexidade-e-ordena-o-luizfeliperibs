import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;


public class Bubblesort<T extends Comparable<T>> implements IOrdenador<T>{

	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;	
	
	public Bubblesort() {
		comparacoes = 0;
		movimentacoes = 0;
	}
	
	@Override
	public T[] ordenar(T[] dados) {
		return ordenar(dados, T::compareTo);
	}

	@Override
	public T[] ordenar(T[] dados, Comparator<T> comparador) {
		T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
		int tamanho = dadosOrdenados.length;
		
		inicio = System.nanoTime();
		
		for (int posReferencia = tamanho - 1; posReferencia > 0; posReferencia--) {
			int trocas = 0;
			for (int posicao = 0; posicao < posReferencia; posicao++) {
				comparacoes++;
				if (comparador.compare(dadosOrdenados[posicao], dadosOrdenados[posicao+1]) > 0){
					swap (posicao, posicao + 1, dadosOrdenados);
					trocas++;
				}
			}
			if(trocas == 0 )
				posReferencia = 0;
		}	
		termino = System.nanoTime();

		return dadosOrdenados;
	}

    public double getTempoordenacao() {
        return termino - inicio;
    }

    public double getTempoMs() {
        return (termino - inicio) / 1_000_000.0;
    }
	
	private void swap(int i, int j, T[] vet) {
		movimentacoes++;
		
		T temp = vet[i];
	    vet[i] = vet[j];
	    vet[j] = temp;
	}
	
	public long getComparacoes() {
		return comparacoes;
	}
	
	public long getMovimentacoes() {
		return movimentacoes;
	}
	
	public double getTempoOrdenacao() {
	    return  0;
	}
}