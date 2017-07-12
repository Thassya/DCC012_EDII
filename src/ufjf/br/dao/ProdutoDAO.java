/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.dao;

import ufjf.br.modelos.Produto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import ufjf.br.modelos.TrieTernaria;

/**
 *
 * @author Thassya
 */
public class ProdutoDAO implements iProdutoDAO {

    private static TrieTernaria trie;

    public ProdutoDAO() {
        this.trie = new TrieTernaria();
    }

    public static TrieTernaria getTrie() {
        return trie;
    }

    public static void setTrie(TrieTernaria trie) {
        ProdutoDAO.trie = trie;
    }

    @Override
    public void Insere(Produto p) throws Exception {
        trie.insere(p);
    }

    @Override
    public List<Produto> getTodos() {
        if (trie.getRaiz() == null) {
            lerArquivo();
        }

        return trie.getProdutos();
    }

    public void lerArquivo() {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Thassya\\Documents\\GitHub\\DCC012_EDII\\src\\resources\\_BancoDeDados.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha = bufferedReader.readLine();
            int count = 1;
            while (linha != null) {
                if (linha != null) {
                    String[] produto = linha.split(";");
                    Produto p = new Produto();
                    p.setNome(produto[0]);
                    p.setCategoria(produto[1]);
                    p.setPreco(produto[2]);
                    p.setDescricao(produto[3]);
                    p.setId(count);
                    trie.insere(p);
                }
                count++;
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
        return trie.getProdutosPorNome();
    }

    public List<Produto> getTodosPorNomeDescrescente() {
        return trie.getProdutosPorNomeDescrescente();
    }

    public List<Produto> getTodosPorCategoria() {
        return trie.getProdutosPorCategoria();
    }

    public List<Produto> getTodosPorCategoriaDescrescente() {
        return trie.getProdutosPorCategoriaDescrescente();
    }

    public List<Produto> getTodosPorPreco() {
        return trie.getProdutosPorCategoria();
    }

    public List<Produto> getTodosPorPrecoDecrescente() {
        return trie.getProdutosPorCategoria();
    }

    public static TrieTernaria getProdutoList() {
        return trie;
    }

    public static void setProdutoList(TrieTernaria produtoList) {
        ProdutoDAO.trie = produtoList;
    }
}
