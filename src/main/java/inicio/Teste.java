package inicio;

public class Teste {
	
	 private static final int tamMax = 10;

	public static void main(String[] args) {
		
        Apartamentos aps = new Apartamentos(tamMax);	
        boolean[] taxasPagas = {true, true, true, true, true, true, true, true, true, true, true, false};
		
		
        
        try {
        	aps.insere(101, "Clarissa", "11111", taxasPagas);
			aps.insere(102, "Fabio", "11111", taxasPagas);
			aps.insere(103, "Beatriz", "2222", taxasPagas);
			aps.insere(104, "Gustavo", "2222", taxasPagas);
			aps.insere(201, "Weber", "2222", taxasPagas);
			aps.insere(202, "Ana", "2222", taxasPagas);
			aps.insere(203, "Vitor", "2222", taxasPagas);
			aps.insere(204, "Anacleto", "2222", taxasPagas);
		} catch (Exception e) {
			System.out.println("Não foi possível inserir mais apartamentos");
		}
      

        aps.ordenaAptosPornome();
        
        int indice = aps.buscaBinariaRecursivaPorNome("Beatriz", 0, aps.indiceMoradores.length -1);

        System.out.println("idice =  "+ indice);
        //        aps.verIndices();
//        System.out.println("\n--------------------------------------------------------------------\n");
       
        
        
        
        
        
	}

}
