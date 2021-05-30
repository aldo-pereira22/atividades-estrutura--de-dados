package inicio;

import javax.swing.JOptionPane;

public class AtividadeDeReposicao {

    private static final int tamMax = 8;

    public static void main(String[] args) {
        Apartamentos aptos = new Apartamentos(tamMax);
        boolean[] taxasPagas = {true, true, true, true, true, true, true, true, true, true, true, false};
        
        char continuaSN = 'n';
        String nome;

        try {
            aptos.insere(102, "Maria Chiquinha", "2", taxasPagas);
            aptos.insere(103, "Zé Roela", "3", taxasPagas);
            aptos.insere(104, "John Doe", "4", taxasPagas);
            aptos.insere(201, "Joane Doe", "5", taxasPagas);
            aptos.insere(203, "Bon Diovi", "7", taxasPagas);
            aptos.insere(202, "Little John Armless", "6", taxasPagas);
            aptos.insere(204, "Vitor Faizano", "8", taxasPagas);
            aptos.insere(101, "Anacleto Cebolão", "1", taxasPagas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        System.out.println("Apartamentos fora de ordem!");
        aptos.imprimeApartamentos();

        //aptos.ordenaPeloMetodoDaBolha();
        //aptos.ordenaPorSelecao();
        aptos.ordenaPorInsercao();
        System.out.println("Apartamentos em ordem!");
        aptos.imprimeApartamentos();
        //Esta parte serve para o discente testar se o seu código de ordenação tá ok.
        System.out.println("Apartamentos por ordem de morador!");
        aptos.imprimeAptosOrdenadosPorNome();
        
        //Pesquisa apto por nome.
        //Eu deixei esta parte totalmente pronta para o discente poder testar o código de busca que implementar.
        do {
            nome = JOptionPane.showInputDialog("Informe o nome para a busca:");
            Apartamento aptoEncontrado = aptos.buscaBinariaPorNome(nome);
            if (aptoEncontrado == null) { //Não encontrou.
                JOptionPane.showMessageDialog(null, "O apartamento não foi encontrado!");
            } else { //Encontrou...
                JOptionPane.showMessageDialog (null, "Apto: " + aptoEncontrado.numero + "\nFone: " + aptoEncontrado.telefone);
            }
            continuaSN = JOptionPane.showInputDialog("Deseja pesquisar outro morador? (s/n)").charAt(0);
        } while (continuaSN == 's');
    }
}
