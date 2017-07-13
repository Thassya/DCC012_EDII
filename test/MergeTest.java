
import java.util.ArrayList;
import org.junit.Test;
import ufjf.br.modelos.Merge;
import ufjf.br.modelos.Produto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thassya
 */
public class MergeTest {

    private Merge merge = new Merge();

    @Test
    public void main() {
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
        listaProdutos.add(new Produto("tomate", "esporte", "a definir", (float) 13.3));
        listaProdutos.add(new Produto("abacate", "esporte", "a definir", (float) 13.3));
        listaProdutos.add(new Produto("abacaxi", "esporte", "a definir", (float) 13.3));
        listaProdutos.add(new Produto("carambola", "esporte", "a definir", (float) 13.3));
        listaProdutos.add(new Produto("guarana", "esporte", "a definir", (float) 13.3));
        
        System.out.println("Vetor Não-Ordenado: ");
        for(Produto p : listaProdutos){
            System.out.print(p.getNome() + " , ");
        }
        
        listaProdutos = merge.mergeSortNome(listaProdutos, 0);
        
        System.out.println("");
        System.out.println("");
        System.out.println("Vetor depois da Ordenação: ");
        for(Produto p : listaProdutos){
            System.out.print(p.getNome() + " , ");
        }

    }
}
