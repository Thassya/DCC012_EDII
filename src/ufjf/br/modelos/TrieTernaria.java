/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.modelos;

/**
 *
 * @author Thassya
 */
public class TrieTernaria {
    private NoTrie raiz;
    
    /**
     * Envia produto para cadastro na Trie Ternária
     * @param p Produto para ser inserido na trie
     */
    public void insere(Produto p) {
        raiz = insere(raiz, p.getNome());
    }

    /**
     * Método privado para inserir recursivamente na trie ternária
     * @param r raiz da Trie
     * @param palavra palavra a ser inserida
     * @return uma NoTrie 
     */
    private NoTrie insere(NoTrie r, String palavra) {
        char caracter = palavra.charAt(0);
        //verifica se a raiz existe
        if (r == null) {
            r = new NoTrie(caracter);
        }
        if (caracter > r.getLetra()) {
            r.setDireita(insere(r.getDireita(), palavra));
        } else if (caracter < r.getLetra()) {
            r.setEsquerda(insere(r.getEsquerda(), palavra));
        } else if (palavra.length() == 1) {
            r.setEhFolha(true);
        } else {
            r.setMeio(insere(r.getMeio(), palavra.substring(1, palavra.length())));
        }
        return r;
    }


    /**
     * Método busca para trie.
     * @param p palavra a ser buscada
     * @return true ou false
     */
    public boolean busca(String p){
        return busca(raiz, p);
    }

    /**
     * Método privado de busca recursiva
     * @param root raiz da trie
     * @param palavra palavra a ser buscada
     * @return retorna true ou false
     */
    private boolean busca(NoTrie root, String palavra){
        char letra = palavra.charAt(0);
        if(root==null) return false;
        else if(root.ehFolha()) return true;
        else if(letra == root.getLetra()) return busca(root.getMeio(),palavra.substring(1,palavra.length()));
        else if(letra>root.getLetra()) return busca(root.getDireita(),palavra.substring(1,palavra.length()));
        else if(letra<root.getLetra()) return busca(root.getEsquerda(),palavra.substring(1,palavra.length()));
        else
            return false;
    }
}
