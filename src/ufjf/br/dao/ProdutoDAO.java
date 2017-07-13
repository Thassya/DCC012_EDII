/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.dao;

import ufjf.br.modelos.Produto;
import ufjf.br.modelos.Merge;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ufjf.br.modelos.TrieTernaria;

/**
 *
 * @author Thassya
 */
public class ProdutoDAO implements iProdutoDAO {

    private static TrieTernaria trieProduto;
    private static TrieTernaria trieCategoria;

    private Merge merge;

    public ProdutoDAO() {
        this.trieCategoria = new TrieTernaria();
        this.trieProduto = new TrieTernaria();
        merge = new Merge();
    }   

    @Override
    public void insere(Produto p) throws Exception {
        trieProduto.insere(p);
    }

    @Override
    public List<Produto> getTodos() {
        if (trieProduto.getRaizProd() == null) {
            lerArquivo();
        }
        return trieProduto.getProdutos();
    }
    
    @Override
    public void lerArquivo() {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Thassya\\Documents\\GitHub\\DCC012_EDII\\src\\resources\\_50mil.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha = bufferedReader.readLine();
            while (linha != null) {
                if (linha != null) {
                    String[] produto = linha.split(";");
                    Produto p = new Produto();
                    p.setNome(produto[0]);
                    p.setCategoria(produto[1]);
                    String aux = produto[2].replace(",", ".");
                    p.setPreco(Float.parseFloat(aux));
                    p.setDescricao(produto[3]);
                    trieProduto.insere(p);
                    trieCategoria.insereCat(p);
                }
                linha = bufferedReader.readLine();
            }
            bufferedReader.close();
            System.out.println("LIDO!");
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado!!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Produto> getTodosPorNome() {
        return trieProduto.getProdutosPorNome();
    }

    public List<Produto> getTodosPorNomeDescrescente() {
        return trieProduto.getProdutosPorNomeDes();
    }

    public List<Produto> getTodosPorCategoria() {
        return trieProduto.getProdutosPorCategoria();
    }

    public List<Produto> getTodosPorCategoriaDescrescente() {
        return trieProduto.getProdutosPorCategoriaDes();
    }

    public List<Produto> getTodosPorPreco() {
        return trieProduto.getProdutosPorPreco();
    }

    public List<Produto> getTodosPorPrecoDecrescente() {
        return trieProduto.getProdutosPorPrecoDes();
    }

    /**
     * Método de orneação do resultado da busca por preço
     * @param p ArrayList de produtos a serem ordenados
     * @return ArrayList de produtos ordenados de maneira Crescente
     */
    public ArrayList<Produto> getOrdenaResultadoCres(ArrayList<Produto> p) {
        merge.mergeSortPreco(p, 0);
        return p;
    }

    /**
     * Método de orneação do resultado da busca por preço
     * @param p ArrayList de produtos a serem ordenados
     * @return ArrayList de produtos ordenados de maneira Decrescente
     */
    public ArrayList<Produto> getOrdenaResultadoDecres(ArrayList<Produto> p) {
        merge.mergeSortPreco(p, 1);
        return p;
    }

    public static TrieTernaria getProdutoList() {
        return trieProduto;
    }

    public static void setProdutoList(TrieTernaria produtoList) {
        ProdutoDAO.trieProduto = produtoList;
    }
    
     public static TrieTernaria getTrieProduto() {
        return trieProduto;
    }

    public static TrieTernaria getTrieCategoria() {
        return trieCategoria;
    }
}
