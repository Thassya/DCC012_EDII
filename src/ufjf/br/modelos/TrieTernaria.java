/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Thassya
 */
public class TrieTernaria {

    private NoTrie raiz;
    private int quantidade;
    private static ArrayList<Produto> filtroProdutos;
    private ArrayList<Produto> autoCompletar;

    /**
     * For best performance, strings should be inserted into the ternary tree in
     * a random order. In particular, do not insert strings in the alphabetical
     * order. Each mini-tree that corresponds to a single trie node would
     * degenerate into a linked list, significantly increasing the cost of
     * lookups. Of course, more complex self-balancing ternary trees can be
     * implemented as well.
     */
    public TrieTernaria() {
        this.filtroProdutos = new ArrayList<Produto>();
        this.autoCompletar = new ArrayList<>();
        this.quantidade = 0;
    }

    public List<Produto> AutoComplete(String palavra) {
        //if (s == null || s == "") { throw new ArgumentException();}
        List<Produto> suggestions = new ArrayList();

        int pos = 0;
        NoTrie root = raiz;
        while (root != null) {
            char letra = palavra.charAt(pos);
            if (letra > root.getLetra()) {
                root = root.getDireita();
            } else if (letra < root.getLetra()) {
                root = root.getEsquerda();
            } else {
                if (++pos == palavra.length()) {
                    if (root.ehFolha()) {
                        suggestions.add(root.getProduto());
                    }
                    FindSuggestions(palavra, suggestions, root.getMeio());
                    return (suggestions);
                }
                root = root.getMeio();
            }
        }
        return (suggestions);
    }

    private void FindSuggestions(String palavra, List<Produto> suggestions, NoTrie root) {
        if (root == null) {
            return;
        }

        if (root.ehFolha()) {
            suggestions.add(root.getProduto());
        }

        FindSuggestions(palavra, suggestions, root.getEsquerda());
        FindSuggestions(palavra + root.getLetra(), suggestions, root.getMeio());
        FindSuggestions(palavra, suggestions, root.getDireita());
    }

    /**
     * Envia produto para cadastro na Trie Ternária
     *
     * @param p Produto para ser inserido na trie
     */
    public void insere(Produto p) {
        raiz = insere(raiz, p.getNome(), p);
        filtroProdutos.add(p);
        incrementaQuantidade();
    }

    /**
     * Método privado para inserir recursivamente na trie ternária
     *
     * @param r raiz da Trie
     * @param palavra palavra a ser inserida
     * @return uma NoTrie
     */
    private NoTrie insere(NoTrie r, String palavra, Produto p) {
        char caracter = palavra.charAt(0);
        //verifica se a raiz existe
        if (r == null) {
            r = new NoTrie(caracter);
        }
        if (caracter > r.getLetra()) {
            r.setDireita(insere(r.getDireita(), palavra, p));
        } else if (caracter < r.getLetra()) {
            r.setEsquerda(insere(r.getEsquerda(), palavra, p));
        } else if (palavra.length() == 1) {
            r.setProduto(p);
        } else {
            r.setMeio(insere(r.getMeio(), palavra.substring(1, palavra.length()), p));
        }
        return r;
    }

    private void ordenarPorNome(List<Produto> produtos) {

        Collections.sort(produtos, (Object o1, Object o2) -> {
            Produto p1 = (Produto) o1;
            Produto p2 = (Produto) o2;

            int res = String.CASE_INSENSITIVE_ORDER.compare(p1.getNome(), p2.getNome());
            return (res != 0) ? res : p1.getNome().compareTo(p2.getNome());
        });
    }

    private void ordenarPorNomeDescrescente(List<Produto> produtos) {

        Collections.sort(produtos, (Object o1, Object o2) -> {
            Produto p1 = (Produto) o1;
            Produto p2 = (Produto) o2;

            int res = String.CASE_INSENSITIVE_ORDER.reversed().compare(p1.getNome(), p2.getNome());
            return (res != 0) ? res : p1.getNome().compareTo(p2.getNome());
        });
    }

    private void ordenarPorCategoria(List<Produto> produtos) {

        Collections.sort(produtos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Produto p1 = (Produto) o1;
                Produto p2 = (Produto) o2;

                return p1.getCategoria().compareTo(p2.getCategoria());
            }
        });
    }

    private void ordenarPorCategoriaDescrescente(List<Produto> produtos) {

        Collections.sort(produtos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Produto p1 = (Produto) o1;
                Produto p2 = (Produto) o2;

                int res = String.CASE_INSENSITIVE_ORDER.reversed().compare(p1.getCategoria(), p2.getCategoria());
                return (res != 0) ? res : p1.getCategoria().compareTo(p2.getCategoria());
            }
        });
    }

    private void ordenarPorPreco(List<Produto> produtos) {

        Collections.sort(produtos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Produto p1 = (Produto) o1;
                Produto p2 = (Produto) o2;

                return p1.getPreco().compareTo(p2.getPreco());
            }
        });
    }

    private void ordenarPorPrecoDescrescente(List<Produto> produtos) {

        Collections.sort(produtos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Produto p1 = (Produto) o1;
                Produto p2 = (Produto) o2;

                int res = String.CASE_INSENSITIVE_ORDER.reversed().compare(p1.getPreco(), p2.getPreco());
                return (res != 0) ? res : p1.getPreco().compareTo(p2.getPreco());
            }
        });
    }

    public NoTrie getRaiz() {
        return raiz;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static ArrayList<Produto> getFiltroProdutos() {
        return filtroProdutos;
    }

    public static void setFiltroProdutos(ArrayList<Produto> filtroProdutos) {
        TrieTernaria.filtroProdutos = filtroProdutos;
    }

    public void incrementaQuantidade() {
        this.quantidade += 1;
    }

    /**
     * Retorna todos os Produtos
     *
     * @return Retorna todos os Produtos
     */
    public ArrayList<Produto> getProdutos() {
        return this.filtroProdutos;
    }

    /**
     * Retorna todos os produtos da Trie ordenados por nome
     *
     * @return Retorna todos os produtos da Trie ordenados por nome
     */
    public ArrayList<Produto> getProdutosPorNome() {
        ordenarPorNome(filtroProdutos);
        return filtroProdutos;
    }

    public ArrayList<Produto> getProdutosPorNomeDescrescente() {
        ordenarPorNomeDescrescente(filtroProdutos);
        return filtroProdutos;
    }

    /**
     * Retorna todos os produtos da Trie ordenados por categoria
     *
     * @return Retorna todos os produtos da Trie ordenados por categoria
     */
    public ArrayList<Produto> getProdutosPorCategoria() {
        //ArrayList<Produto> produtos = getProdutos();
        ordenarPorCategoria(filtroProdutos);
        return filtroProdutos;
    }

    public ArrayList<Produto> getProdutosPorCategoriaDescrescente() {
        //ArrayList<Produto> produtos = getProdutos();
        ordenarPorCategoriaDescrescente(filtroProdutos);
        return filtroProdutos;
    }

    /**
     * Retorna todos os produtos da Trie ordenados por preço
     *
     * @return Retorna todos os produtos da Trie ordenados por preço
     */
    public ArrayList<Produto> getProdutosPorPreco() {
        //ArrayList<Produto> produtos = getProdutos();
        ordenarPorPreco(filtroProdutos);
        return filtroProdutos;
    }

    public ArrayList<Produto> getProdutosPorPrecoDescrescente() {
        //ArrayList<Produto> produtos = getProdutos();
        ordenarPorPrecoDescrescente(filtroProdutos);
        return filtroProdutos;
    }
}
