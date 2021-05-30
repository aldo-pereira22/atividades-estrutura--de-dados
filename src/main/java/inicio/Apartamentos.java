package inicio;

public class Apartamentos {

	private Apartamento[] apartamentos;
	public IndiceMorador[] indiceMoradores;

	private int qtdeAptos;
	private int qtdeMaxima;

	public Apartamentos(int qtdeMaxima) {
		this.apartamentos = new Apartamento[qtdeMaxima];
		this.indiceMoradores = new IndiceMorador[qtdeMaxima];
		this.qtdeAptos = 0;
		this.qtdeMaxima = qtdeMaxima;
	}

	public void insere(int numero, String nome, String telefone, boolean taxaPaga[]) throws Exception {
		if (this.qtdeAptos == this.qtdeMaxima) {
			throw new Exception("Quantidade máxima de apartamentos atingina. Não possível inserir novos apartamentos.");
		}

		this.apartamentos[this.qtdeAptos] = new Apartamento(numero, nome, telefone, taxaPaga);
		this.qtdeAptos++;
	}

	public boolean exclui(int numero) {
		int aptoEncontrado = 0;
		boolean achou = false;

		for (int apto = 0; apto < this.qtdeAptos && !achou; apto++) {
			if (this.apartamentos[apto].numero == numero) {
				aptoEncontrado = apto;
				achou = true;
			}
		}

		if (achou) {
			for (int apto = aptoEncontrado; apto < (this.qtdeAptos - 1); apto++) {
				this.apartamentos[apto] = this.apartamentos[apto + 1];
			}
			this.qtdeAptos--;
			// Limpa da memória o último apto.
			this.apartamentos[this.qtdeAptos] = new Apartamento();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Busca sequencialmente por apartamento de um determinado número.
	 *
	 * @param numero Número do apartamento.
	 * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
	 */
	public Apartamento buscaLlinear(int numero) {
		for (int apto = 0; apto < this.qtdeAptos; apto++) {
			if (this.apartamentos[apto].numero == numero) {
				return this.apartamentos[apto];
			}
		}

		return null;
	}

	/**
	 * Busca binariamente por apartamento de um determinado número.
	 * 
	 * @param numero Número do apartamento.
	 * @return Objeto Apartamento encontrado ou null em caso de não encontrar.
	 */
	public Apartamento buscaBinaria(int numero) {
		int inicio = 0;
		int fim = this.apartamentos.length - 1;
		int meio;
		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			if (this.apartamentos[meio].numero == numero) {
				return this.apartamentos[meio];
			}
			if (numero < this.apartamentos[meio].numero) {
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}

		return null;
	}

	/**
	 * Ordena primeiramete o vetor apartamentos e depois o vetor de índice de nomes
	 **/
	public void ordenaPeloMetodoDaBolha() {
		for (int dir = this.qtdeAptos - 1; dir > 0; dir--) {
			for (int esq = 0; esq < dir; esq++) {
				if (apartamentos[esq].numero > apartamentos[esq + 1].numero) {
					this.trocaAptos(esq, esq + 1);
				}
			}
		}

		// Cria o indice ordenado por nome
		this.ordenaAptosPorNome();
	}

	/**
	 * Ordena primeiramete o vetor apartamentos e depois o vetor de índice de nomes
	 **/
	public void ordenaPorSelecao() {
		int indiceDoMenor, interno;

		for (int externo = 0; externo < this.qtdeAptos - 1; externo++) {
			indiceDoMenor = externo;
			for (interno = externo + 1; interno < this.qtdeAptos; interno++) {
				if (apartamentos[interno].numero < apartamentos[indiceDoMenor].numero) {
					indiceDoMenor = interno;
				}
			}

			if (indiceDoMenor == externo) {
				// troca desnecessária...
			} else {
				this.trocaAptos(indiceDoMenor, externo);
			}
		}

		// Cria o indice ordenado por nome
		this.ordenaAptosPorNome();
	}

	private void trocaAptos(int i, int ii) {
		Apartamento temp = this.apartamentos[i];
		this.apartamentos[i] = this.apartamentos[ii];
		this.apartamentos[ii] = temp;
	}

	/**
	 * Ordena primeiramete o vetor apartamentos e depois o vetor de índice de nomes
	 **/
	public void ordenaPorInsercao() {
		int interno, externo;

		for (externo = 1; externo < this.qtdeAptos; externo++) {
			Apartamento aux = this.apartamentos[externo];

			for (interno = externo; interno > 0 && this.apartamentos[interno - 1].numero >= aux.numero; interno--) {
				this.apartamentos[interno] = this.apartamentos[interno - 1];
			}

			this.apartamentos[interno] = aux;
		}

		// Cria o indice ordenado por nome
		this.ordenaAptosPorNome();
	}

	/**
	 * Métodos para ordenação e busca por nome.
	 */
	private void ordenaAptosPorNome() {

	}

	/*
	 * Gera o vetor indiceMoradores.
	 */
	private void geraIndiceDeMoradores() {

		for (int i = 0; i < apartamentos.length; i++) {

			if (apartamentos[i] != null) {
				IndiceMorador indiceMorador = new IndiceMorador(apartamentos[i].nome, i);
				this.indiceMoradores[i] = indiceMorador;

			}
		}

	}

	/*
	 * Ordena pelo Método de Inserção o vetor indiceMoradores.
	 */
//	private void ordenaNomesPorInsercao() {
//		
//		// Variável que conta somente os apartamentos que estão ocupados.
//		int contador = 0;
//		for(int i = 0; i < apartamentos.length;i++) {
//			if(apartamentos[i] != null) {
//				contador++;
//			}
//		} 
//		
//		// Vetor de string para obter os nomes dos apartamentos..
//		String[] nomes = new String[contador];
//
//		for (int i = 0; i < apartamentos.length; i++) {
//			
//			// Garantindo que não vai pegar objetos nulos e evitar NullPointer
//			if(apartamentos[i] != null ) {
//				
//				nomes[i] = apartamentos[i].nome;
//			}
//		}
//
//		Arrays.sort(nomes);
//		
//		for(int i = 0; i < nomes.length; i++) {
//			Apartamento aux = new Apartamento();
//			
//			if( !(nomes[i].equals(apartamentos[i].nome))) {
//				
//				for(int j = i+1; j < nomes.length; j++) {
//					
//					if(nomes[i].equals(apartamentos[j].nome)) {
//						
//						aux = apartamentos[i];
//						apartamentos[i] = apartamentos[j];
//						apartamentos[j] = aux;
//						
//					}	
//				}
//			}
//			
//		}
//				
////		System.out.println("OBJETOS\n");
////		for (int i = 0; i < nomes.length; i++) {
////			System.out.println(apartamentos[i].nome);
////		}
////		System.out.println("-------------------------------------------");
////		System.out.println("\n STRINGS ");
////		for (int i = 0; i < nomes.length; i++) {
////			System.out.println(nomes[i]);
////		}
//
//	}

	// MÈTODO PARA CRIAR A ORDENAÇÃO no ARRAY DE APARTAMENTOS
	private void ordenaNomesPorInsercao1() {
		geraIndiceDeMoradores();
		Apartamento aux = new Apartamento();
		int j;

		for (int i = 1; i < apartamentos.length; i++) {
			if (apartamentos[i] != null) {

				aux = apartamentos[i];
				j = i - 1;
				while (j >= 0 && (apartamentos[j].nome.compareTo(aux.nome)) > 0) {
					apartamentos[j + 1] = apartamentos[j];
					j--;
				}
				apartamentos[j + 1] = aux;
			}
		}

	}

	private void ordenaNomesPorInsercao() {
//		System.out.println("INDICE DE MORADORES SEM ORDENAÇÃO");
//		for(int i = 0; i < indiceMoradores.length; i++) {
//			if(indiceMoradores[i] != null ) {
//				System.out.println("Nome: " +indiceMoradores[i].nome);
//			}
//		}

		IndiceMorador aux = new IndiceMorador();
		int j;

		for (int i = 1; i < indiceMoradores.length; i++) {
			if (indiceMoradores[i] != null) {

				aux = indiceMoradores[i];
				j = i - 1;
				while (j >= 0 && (indiceMoradores[j].nome.compareTo(aux.nome)) > 0) {
					indiceMoradores[j + 1] = indiceMoradores[j];
					j--;
				}
				indiceMoradores[j + 1] = aux;
			}
		}


	}

	/**
	 * Busca pelo nome no índice de nomes.
	 *
	 * return Apartamento Se encontrar retorna o objeto apartamentos recuperado do
	 * vetor de apartamentos. Se não encontrar retorna null.
	 */
	public Apartamento buscaBinariaPorNome(String nome) {
		// Deve ser implementado.
		// Deve usar o método buscaBinariaRecursivaPorNome (String nome, int ini, int
		// fim).
		int indice = buscaBinariaRecursivaPorNome(nome, 0, indiceMoradores.length - 1);
		int inicio = 0;
		int fim = apartamentos.length -1;
		
		while( inicio <=  fim) {
			
			int meio = (inicio + fim) / 2;
			
			if(meio == indice) {
				return apartamentos[meio];
			}else if(meio < indice) {
				inicio  = meio +1;
			}else if( meio > indice) {
				fim = meio -1;
			}
			
		}
		
		return null;
	}

	/**
	 * Busca o nome no índice de nomes.
	 *
	 * return int Se encontrar retorna o índice para o array de apartamentos. Se não
	 * encontrar retorna um índice inválido (quantidade total de apartamentos).
	 */
	private int buscaBinariaRecursivaPorNome(String nome, int ini, int fim) {
		// Deve ser implementado.
		// Este método deve ser recursivo, ou seja, no lugar de um laço de repetição
		// implementar uma função recursiva.
		int meio = ((int) (ini + fim) / 2);

		if (ini > fim) {
			return indiceMoradores.length;
		}

		if (indiceMoradores[meio] != null && indiceMoradores[meio].nome.equals(nome)) {
			return indiceMoradores[meio].indice;
		}
		if (indiceMoradores[meio] != null && indiceMoradores[meio].nome.compareTo(nome) < 0) {
			return buscaBinariaRecursivaPorNome(nome, meio + 1, fim);
		} else {
			return buscaBinariaRecursivaPorNome(nome, ini, meio - 1);
		}
	}

// Implementei este método para auxiliar o discente nas suas checagens.
	public void imprimeAptosOrdenadosPorNome() {
		for (int i = 0; i < this.qtdeAptos; i++) {
			this.apartamentos[indiceMoradores[i].indice].imprimeDadosReduzidos();
		}
	}

	/**
	 * Fim dos métodos para ordenação e busca por nome.
	 */
	public void imprimeApartamentos() {

		for (int apto = 0; apto < this.qtdeAptos; apto++) {

			this.apartamentos[apto].imprimeApartamento();
		}
	}

	public void verIndices() {
		for (int i = 0; i < indiceMoradores.length; i++) {
			if (indiceMoradores[i] != null) {
				System.out.println("" + indiceMoradores[i].nome + " Indice = " + indiceMoradores[i].indice);

			}
		}
	}
	
	public void imprimeIndiceMoradores() {

		System.out.println("-----------------------------------------------------");
		System.out.println("INDICE DE MORADORES COM ORDENAÇÃO");
		for(int i = 0; i < indiceMoradores.length; i++) {
			if(indiceMoradores[i] != null ) {
				System.out.println("Nome: " +indiceMoradores[i].nome);
				System.out.println("Indice: " +indiceMoradores[i].indice+"\n");
			}
		}
	}

	public void imprimeDadosReduzidos() {
		for (int apto = 0; apto < this.qtdeAptos; apto++) {
			this.apartamentos[apto].imprimeDadosReduzidos();
		}
	}

	public void ordenaAptosPornome() {
		geraIndiceDeMoradores();
		ordenaNomesPorInsercao();
	}

}
