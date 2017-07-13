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
public class NoTrie {

    private char letra;
    private int value = 1;
    private boolean ehFolha = false;
    private NoTrie esquerda;
    private NoTrie direita;
    private NoTrie meio;
    private Produto produto; //para ordernar produto!
    
    private List<Produto> listaProduto = new ArrayList<>();
    

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public NoTrie getDireita() {
        return direita;
    }

    public void setDireita(NoTrie direita) {
        this.direita = direita;
    }

    public NoTrie getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoTrie esquerda) {
        this.esquerda = esquerda;
    }

    public NoTrie getMeio() {
        return meio;
    }

    public void setMeio(NoTrie meio) {
        this.meio = meio;
    }

    public void setEhFolha(boolean ehFolha) {
        this.ehFolha = ehFolha;
    }

    public boolean ehFolha() {
        return this.ehFolha;
    }

    public NoTrie() {
    }

    public NoTrie(char letter) {
        this.letra = letter;
    }

    public String toString() {
        return this.letra + "-->" + this.esquerda + "|" + this.meio + "|" + this.direita;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.ehFolha = true;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }
    public void addItemListaProduto(Produto p){
        this.listaProduto.add(p);
        this.ehFolha=true;
    }

}
