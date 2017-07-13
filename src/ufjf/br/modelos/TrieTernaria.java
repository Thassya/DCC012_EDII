/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thassya
 */
public class TrieTernaria {

    private Merge merge;
    private NoTrie raizProd;
    private NoTrie raizCategoria;
    private static ArrayList<Produto> filtroProdutos;

    /**
     * For best performance, strings should be inserted into the ternary tree in
     * a random order. In particular, do not insert strings in the alphabetical
     * order. Each mini-tree that corresponds to a single trie node would
     * degenerate into a linked list, significantly increasing the cost of
     * lookups. Of course, more complex self-balancing ternary trees can be
     * implemented as well.
     */
    public TrieTernaria() {
        this.merge = new Merge();
        this.filtroProdutos = new ArrayList<Produto>();
    }

    public ArrayList<Produto> autoCompleteCategoria(String categoria) {
        List<Produto> aux = buscaCategoria(raizCategoria, categoria);
        ArrayList<Produto> sugestoes = new ArrayList();
        if (aux != null) {
            sugestoes = (ArrayList) aux;
            return sugestoes;
        }
        //Caso de procurar uma palavra que não é folha
        NoTrie root = buscaUltimaPosicao(raizCategoria, categoria);
        FillSuggestionsCat(sugestoes, root);

        if (sugestoes.isEmpty()) {
            System.out.println("Não encontrou correspondencia!");
            FindSuggestionsCat(categoria, sugestoes, raizCategoria);
        }
        return sugestoes;
    }

    public ArrayList<Produto> autoCompleteProduto(String palavra) {
        Produto aux = busca(raizProd, palavra);
        ArrayList<Produto> sugestoes = new ArrayList();
        if (aux != null) {
            sugestoes.add(aux);
        }

        int pos = 0;
        NoTrie root = raizProd;
        while (root != null) {
            char letra = palavra.charAt(pos);
            if (letra > root.getLetra()) {
                root = root.getDireita();
            } else if (letra < root.getLetra()) {
                root = root.getEsquerda();
            } else {
                if (++pos == palavra.length()) {
                    if (root.ehFolha()) {
                        sugestoes.add(root.getProduto());
                    }
                    FindSuggestions(palavra, sugestoes, root.getMeio());
                    return (sugestoes);
                }
                root = root.getMeio();
            }
        }
        //Caso de procurar uma palavra que não é folha
        System.out.println("chamou fill suggestions;");
        root = buscaUltimaPosicao(raizProd, palavra);
        FillSuggestions(sugestoes, root);

        if (sugestoes.isEmpty()) {
            System.out.println("Não encontrou correspondencia!");
            FillSuggestions(sugestoes, raizProd);
        }
        return (sugestoes);
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

    private void FindSuggestions2(String palavra, List<Produto> suggestions, NoTrie root) {
        NoTrie rootAux = root;
        try {
            while (rootAux != null) {
                char letra = palavra.charAt(0);
                while (letra > rootAux.getLetra()) {
                    rootAux = rootAux.getDireita();
                    if (letra == rootAux.getLetra()) {
                        break;
                    }
                }
                while (letra < rootAux.getLetra()) {
                    rootAux = rootAux.getEsquerda();
                    if (letra == rootAux.getLetra()) {
                        break;
                    }
                }
                if (letra == rootAux.getLetra()) {
                    while (!rootAux.ehFolha()) {
                        rootAux = rootAux.getMeio();
                    }
                }
                for (Produto p : rootAux.getListaProduto()) {
                    suggestions.add(p);
                }
                break;
            }
        } catch (Exception e) {
            FillSuggestions(suggestions, raizCategoria);
        }
    }

    private void FindSuggestionsCat(String palavra, List<Produto> suggestions, NoTrie root) {
        if (root == null) {
            return;
        }

        NoTrie rootAux = root;
        try {
            while (rootAux != null) {
                char letra = palavra.charAt(0);
                while (letra > rootAux.getLetra()) {
                    rootAux = rootAux.getDireita();
                    if (letra == rootAux.getLetra()) {
                        break;
                    }
                }
                while (letra < rootAux.getLetra()) {
                    rootAux = rootAux.getEsquerda();
                    if (letra == rootAux.getLetra()) {
                        break;
                    }
                }
                if (letra == rootAux.getLetra()) {
                    while (!rootAux.ehFolha()) {
                        rootAux = rootAux.getMeio();
                    }
                }
                for (Produto p : rootAux.getListaProduto()) {
                    suggestions.add(p);
                }
                break;
            }
        } catch (Exception e) {
            FillSuggestionsCat(suggestions, raizCategoria);
        }
    }

    private void FillSuggestionsCat(List<Produto> suggestions, NoTrie root) {
        if (root == null) {
            return;
        }
        if (!root.getListaProduto().isEmpty()) {
            for (Produto p : root.getListaProduto()) {
                suggestions.add(p);
            }
        }
        FillSuggestionsCat(suggestions, root.getEsquerda());
        FillSuggestionsCat(suggestions, root.getMeio());
        FillSuggestionsCat(suggestions, root.getDireita());
    }

    private void FillSuggestions(List<Produto> suggestions, NoTrie root) {
        if (root == null) {
            return;
        }
        if (root.getProduto() != null) {
            suggestions.add(root.getProduto());
        }
        FillSuggestions(suggestions, root.getEsquerda());
        FillSuggestions(suggestions, root.getDireita());
        FillSuggestions(suggestions, root.getMeio());

    }

    private NoTrie buscaUltimaPosicao(NoTrie root, String palavra) {
        if (root == null) {
            return null;
        }
        char letra = palavra.charAt(0);
        if (palavra.length() == 1) {
            return root;
        } else if (letra > root.getLetra()) {
            return buscaUltimaPosicao(root.getDireita(), palavra);
        } else if (letra < root.getLetra()) {
            return buscaUltimaPosicao(root.getEsquerda(), palavra);
        } else if (letra == root.getLetra()) {
//            if(root.getMeio().getLetra() != palavra.charAt(1)){
//                return root;
//            }
            return buscaUltimaPosicao(root.getMeio(), palavra.substring(1, palavra.length()));
        }
        return null;
    }

    private List<Produto> buscaCategoria(NoTrie root, String categoria) {
        if (root == null) {
            return null;
        }
        char letra = categoria.charAt(0);
        if (letra > root.getLetra()) {
            return buscaCategoria(root.getDireita(), categoria);
        } else if (letra < root.getLetra()) {
            return buscaCategoria(root.getEsquerda(), categoria);
        } else if (letra == root.getLetra()) {
            if (categoria.length() == 1) {
                if (root.ehFolha()) {
                    return root.getListaProduto();
                } else {
                    return null;
                }
            } else {
                return buscaCategoria(root.getMeio(), categoria.substring(1, categoria.length()));
            }
        }
        return null;
    }

    private Produto busca(NoTrie root, String palavra) {
        if (root == null) {
            return null;
        }
        char letra = palavra.charAt(0);

        if (letra > root.getLetra()) {
            return busca(root.getDireita(), palavra);
        } else if (letra < root.getLetra()) {
            return busca(root.getEsquerda(), palavra);
        } else if (letra == root.getLetra()) {
            if (palavra.length() == 1) {
                if (root.ehFolha()) {
                    return root.getProduto();
                } else {
                    return null;
                }
            } else {
                return busca(root.getMeio(), palavra.substring(1, palavra.length()));
            }
        }
        return null;
    }

    public void insereCat(Produto p) {
        raizCategoria = insereCat(raizCategoria, p.getCategoria(), p);
    }

    private NoTrie insereCat(NoTrie r, String cat, Produto p) {
        char caracter = cat.charAt(0);
        if (r == null) {
            r = new NoTrie(caracter);
        }
        if (caracter > r.getLetra()) {
            r.setDireita(insereCat(r.getDireita(), cat, p));
        } else if (caracter < r.getLetra()) {
            r.setEsquerda(insereCat(r.getEsquerda(), cat, p));
        } else if (cat.length() == 1) {
            r.addItemListaProduto(p);
        } else {
            r.setMeio(insereCat(r.getMeio(), cat.substring(1, cat.length()), p));
        }
        return r;
    }

    /**
     * Envia produto para cadastro na Trie Ternária
     *
     * @param p Produto para ser inserido na trie
     */
    public void insere(Produto p) {
        raizProd = insere(raizProd, p.getNome(), p);
        filtroProdutos.add(p);
    }

    /**
     * Método privado para inserir recursivamente na trie ternária
     *
     * @param r raizProd da Trie
     * @param palavra palavra a ser inserida
     * @return uma NoTrie
     */
    private NoTrie insere(NoTrie r, String palavra, Produto p) {
        char caracter = palavra.charAt(0);
        //verifica se a raizProd existe
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

    public NoTrie getRaizProd() {
        return raizProd;
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
        filtroProdutos = merge.mergeSortNome(filtroProdutos, 0);
        return filtroProdutos;
    }

    public ArrayList<Produto> getProdutosPorNomeDes() {
        filtroProdutos = merge.mergeSortNome(filtroProdutos, 1);
        return filtroProdutos;
    }

    /**
     * Retorna todos os produtos da Trie ordenados por Categoria
     *
     * @return Retorna todos os produtos da Trie ordenados por nome
     */
    public ArrayList<Produto> getProdutosPorCategoria() {
        filtroProdutos = merge.mergeSortCat(filtroProdutos, 0);
        return filtroProdutos;
    }

    public ArrayList<Produto> getProdutosPorCategoriaDes() {
        filtroProdutos = merge.mergeSortCat(filtroProdutos, 1);
        return filtroProdutos;
    }

    /**
     * Retorna todos os produtos da Trie ordenados por Preço
     *
     * @return Retorna todos os produtos da Trie ordenados por nome
     */
    public ArrayList<Produto> getProdutosPorPreco() {
        filtroProdutos = merge.mergeSortPreco(filtroProdutos, 0);
        return filtroProdutos;
    }

    public ArrayList<Produto> getProdutosPorPrecoDes() {
        filtroProdutos = merge.mergeSortPreco(filtroProdutos, 1);
        return filtroProdutos;
    }
}
