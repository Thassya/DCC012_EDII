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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Thassya
 */
public class ProdutoDAO implements iProdutoDAO {

    private static List<Produto> produtoList;

    public ProdutoDAO() {
        this.produtoList = new ArrayList<>();
    }

    @Override
    public void Insere(Produto p) throws Exception {
        produtoList.add(p);
    }

    @Override
    public List<Produto> getTodos() {
        if (!produtoList.isEmpty()) {
            return produtoList;
        } else {
            try {                 
                FileReader fileReader = new FileReader("C:\\Users\\Thassya\\Documents\\GitHub\\DCC012_EDII\\src\\resources\\523595palavras.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String linha = bufferedReader.readLine();
                while (linha != null) {
                    if (linha != null) {
                        String nomeProduto = linha.substring(linha.indexOf("Produto: ") + 9, linha.indexOf(" Categoria:"));            //Separa em substrings específicas de leitura
                        String categoria = linha.substring(linha.indexOf("Categoria: ") + 11, linha.indexOf(" Preço:"));
                        String preco = linha.substring(linha.indexOf("Preço: ") + 7, linha.indexOf(" Descrição:"));
                        String descricao = linha.substring(linha.indexOf("Descrição: ") + 11, linha.length());
                        Produto p = new Produto(nomeProduto, categoria, preco, descricao);

                        produtoList.add(p);
                    }
                    linha = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado!!");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
                e.printStackTrace();
            }
        }
        ordenarPorNome(produtoList);
        return produtoList;
    }

    public void ordenarPorNome(List<Produto> produtos) {

        Collections.sort(produtos, (Object o1, Object o2) -> {
            Produto p1 = (Produto) o1;
            Produto p2 = (Produto) o2;

            return p1.getNome().compareTo(p2.getNome());
        });
    }

    public void ordenarPorCategoria(List<Produto> produtos) {

        Collections.sort(produtos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Produto p1 = (Produto) o1;
                Produto p2 = (Produto) o2;

                return p1.getCategoria().compareTo(p2.getCategoria());
            }
        });
    }

    public static List<Produto> getProdutoList() {
        return produtoList;
    }

    public static void setProdutoList(List<Produto> produtoList) {
        ProdutoDAO.produtoList = produtoList;
    }
}
