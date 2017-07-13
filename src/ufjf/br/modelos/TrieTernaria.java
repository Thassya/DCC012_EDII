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

    /**
     * Função Autocompleta do Produto Busca a palavra na TRIE e busca sugestões.
     * Se não encontra tenta encontrar um valor parecido na trie. Se não existir
     * o valor parecido retorna tudo.
     *
     * @param palavra nome do Produto a ser buscado
     * @return ArrayList de Produtos
     */
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
                    encontraSugestoesProd(palavra, sugestoes, root.getMeio());
                    return (sugestoes);
                }
                root = root.getMeio();
            }
        }
        //Caso de procurar uma palavra que não é folha
        System.out.println("chamou fill suggestions;");
        root = buscaUltimaPosicao(raizProd, palavra);
        preencheSugestoesProd(sugestoes, root);

        if (sugestoes.isEmpty()) {
            System.out.println("Não encontrou correspondencia!");
            preencheSugestoesProd(sugestoes, raizProd);
        }
        return (sugestoes);
    }

    /**
     * A partir do ultimo nó valido encontra todas as folhas.
     *
     * @param palavra - nome do Produto a ser buscado
     * @param sugestoes - ArrayList que salva as sugestoes
     * @param root - último NoTrie válido
     */
    private void encontraSugestoesProd(String palavra, List<Produto> sugestoes, NoTrie root) {
        if (root == null) {
            return;
        }
        if (root.ehFolha()) {
            sugestoes.add(root.getProduto());
        }
        encontraSugestoesProd(palavra, sugestoes, root.getEsquerda());
        encontraSugestoesProd(palavra + root.getLetra(), sugestoes, root.getMeio());
        encontraSugestoesProd(palavra, sugestoes, root.getDireita());
    }

    /**
     * Quando não encontra sugestões preenche a partir da raiz.
     *
     * @param sugestoes ArrayList que salva as sugestoes
     * @param root raiz Trie Produto
     */
    private void preencheSugestoesProd(List<Produto> sugestoes, NoTrie root) {
        if (root == null) {
            return;
        }
        if (root.getProduto() != null) {
            sugestoes.add(root.getProduto());
        }
        preencheSugestoesProd(sugestoes, root.getEsquerda());
        preencheSugestoesProd(sugestoes, root.getDireita());
        preencheSugestoesProd(sugestoes, root.getMeio());

    }

    /**
     * Função de autocomplete da Categoria. Como a categoria tem um array muito
     * grande de produtos, se a pessoa digitar o nome exato, tras todos os
     * produtos daquela categoria e só. Se a pessoa não digitar o nome completo
     * da categoria, busca ultimo "match" valido na TRIE e devolve o resultado
     * para o usuário. Se o nome que o usuário digitar não estiver na TRIE,
     * devolve tudo.
     *
     * @param categoria nome da categoria buscada
     * @return ArrayList de Produtos
     */
    public ArrayList<Produto> autoCompleteCategoria(String categoria) {
        List<Produto> aux = buscaCategoria(raizCategoria, categoria);
        ArrayList<Produto> sugestoes = new ArrayList();
        if (aux != null) {
            sugestoes = (ArrayList) aux;
            return sugestoes;
        }
        //Caso de procurar uma palavra que não é folha
        NoTrie root = buscaUltimaPosicao(raizCategoria, categoria);
        preencheSugestoesCat(sugestoes, root);

        if (sugestoes.isEmpty()) {
            System.out.println("Não encontrou correspondencia!");
            encontraSugestoesCat(categoria, sugestoes, raizCategoria);
        }
        return sugestoes;
    }

    /**
     * Método que, a patir do ultimo nó valido da Trie encontra os produtos
     * folha
     *
     * @param palavra Palavra a ser buscada
     * @param sugestoes ArrayList que salva as sugestoes
     * @param root último NoTrie válido
     */
    private void encontraSugestoesCat(String palavra, List<Produto> sugestoes, NoTrie root) {
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
                    sugestoes.add(p);
                }
                break;
            }
        } catch (Exception e) {
            preencheSugestoesCat(sugestoes, raizCategoria);
        }
    }

    /**
     * Quando não encontra nada na busca anterior, devolve todos os resultados
     *
     * @param sugestoes ArrayList que salva as sugestoes
     * @param root raiz Trie categoria
     */
    private void preencheSugestoesCat(List<Produto> sugestoes, NoTrie root) {
        if (root == null) {
            return;
        }
        if (!root.getListaProduto().isEmpty()) {
            for (Produto p : root.getListaProduto()) {
                sugestoes.add(p);
            }
        }
        preencheSugestoesCat(sugestoes, root.getEsquerda());
        preencheSugestoesCat(sugestoes, root.getMeio());
        preencheSugestoesCat(sugestoes, root.getDireita());
    }

    /**
     * Método que devolve a ultima posição da TRIE válida para a palavra
     * buscada. Serve para Produto e Categoria
     *
     * @param root Raiz da TRIE desejada.
     * @param palavra Palavra a ser buscada
     * @return NoTrie
     */
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

    /**
     * Método de busca para o produto
     *
     * @param root raiz da TRIE
     * @param palavra nome do produto buscado
     * @return Produto
     */
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

    /**
     * Método de busca para a Categoria.
     *
     * @param root raiz da TRIE
     * @param categoria Nome da Categoria buscada
     * @return ArrayList de Produtos
     */
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

    /**
     * Insere Produto por Categoria na Trie Ternária
     *
     * @param p Produto para ser inserido na trie
     */
    public void insereCat(Produto p) {
        raizCategoria = insereCat(raizCategoria, p.getCategoria(), p);
    }

    /**
     * Método privado para inserir recursivamente na trie ternária
     *
     * @param r raizProd da Trie
     * @param cat categoria a ser inserida
     * @return NoTrie
     */
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
     * Insere Produto por Nome do Produto na Trie Ternária
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
