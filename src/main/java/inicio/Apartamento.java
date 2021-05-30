package inicio;

public class Apartamento {

	public int numero;
	public String nome;
	public String telefone;
	public boolean[] taxaPaga;


	public Apartamento() {
		this.taxaPaga = new boolean[12];
	}

	public Apartamento(int numero, String nome, String telefone, boolean taxaPaga[]) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.taxaPaga = taxaPaga;
	}

	public void imprimeApartamento() {
		// monta uma string para mostrar pgtos por mês.
		String mesesPagos = "\nJAN  FEV  MAR  ABR  MAI  JUN  JUL  AGO  SET  OUT  NOV  DEZ\n";

		for (int mes = 0; mes < 12; mes++) {
			if (this.taxaPaga[mes]) {
				mesesPagos += "PG   ";
			} else {
				mesesPagos += "NP   ";
			}
		}

		System.out.println("\n\nApartamento: " + numero + ", morador: " + nome + ", telefone: " + telefone + mesesPagos);
	}

	public void imprimeDadosReduzidos() {
		System.out.println("Apartamento: " + numero + ", morador: " + nome);
	}

}
