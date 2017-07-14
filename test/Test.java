
/**
 *
 * @author Thassya
 */
import ufjf.br.modelos.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import ufjf.br.dao.ProdutoDAO;

public class Test {

    static float mb = 1024 * 1024;	//
    static Runtime runtime = Runtime.getRuntime(); // função de memoria

    public static void imprimeMemoria() {
        System.out.println("Para 300 mil");
        System.out.println("Total: " + runtime.totalMemory() / mb + " MB | Usada: " + ((runtime.totalMemory() - runtime.freeMemory()) / mb) + " MB | Maxima: " + runtime.maxMemory() / mb + " MB");
    }

    public static void main(String[] args) {
        TrieTernaria produtos = new TrieTernaria();
        TrieTernaria categorias = new TrieTernaria();

        double hora = System.currentTimeMillis(); // função de tempo
        try {
            FileReader arq = new FileReader("C:\\Users\\Thassya\\Documents\\GitHub\\DCC012_EDII\\src\\resources\\_50mil.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while (linha != null) {
                String[] produto = linha.split(";");
                Produto p = new Produto();
                p.setNome(produto[0]);
                p.setCategoria(produto[1]);
                String aux = produto[2].replace(",", ".");
                p.setPreco(Float.parseFloat(aux));
                p.setDescricao(produto[3]);
                produtos.insere(p);
                categorias.insereCat(p);
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

//        System.out.println("Buscando o Produto: ferrolho");
//        ArrayList<Produto> pp = produtos.autoCompleteProduto("ferrolho");        
//        System.out.println("Quantidade produtos retornada: " + pp.size());
//        System.out.println("");
//        
//        System.out.println("Buscando a Categoria: Lazer");
//        ArrayList<Produto> aa = categorias.autoCompleteCategoria("Lazer");
//        System.out.println("Quantidade produtos retornada: " + aa.size());

        System.out.println("Ordenando por Nome");
        ArrayList<Produto> aa = produtos.getProdutosPorNome();
   
        hora = ((System.currentTimeMillis() - hora) / 1000); //tempo
        System.out.println("\nTempo decorrido : " + hora + " Segundos\n");
        imprimeMemoria();//memoria

    }

}
