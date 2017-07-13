/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.modelos;

import java.util.ArrayList;

/**
 *
 * @author Thassya
 */
public class Merge {

    //private ArrayList<Produto> strList;
    public Merge() {
    }

    /**
     * Método para ordenar os produtos em ordem crescente de nome
     *
     * @param arrayProduto
     * @param tipo 0 = Crescente, 1 = Descrescente
     * @return
     */
    public ArrayList<Produto> mergeSortNome(ArrayList<Produto> arrayProduto, int tipo) {
        ArrayList<Produto> esquerda = new ArrayList<Produto>();
        ArrayList<Produto> direita = new ArrayList<Produto>();
        int center;

        if (arrayProduto.size() == 1) {
            return arrayProduto;
        } else {
            center = arrayProduto.size() / 2;
            // copy the left half of whole into the left.
            for (int i = 0; i < center; i++) {
                esquerda.add(arrayProduto.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i = center; i < arrayProduto.size(); i++) {
                direita.add(arrayProduto.get(i));
            }

            // Sort the left and right halves of the arraylist.
            esquerda = mergeSortNome(esquerda, tipo);
            direita = mergeSortNome(direita, tipo);

            // Merge the results back together.
            mergeNome(esquerda, direita, arrayProduto, tipo);
        }
        return arrayProduto;
    }

    private void mergeNome(ArrayList<Produto> esquerda, ArrayList<Produto> direita, ArrayList<Produto> arrayProduto, int tipo) {
        int esqIndex = 0;
        int dirIndex = 0;
        int arrayIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (esqIndex < esquerda.size() && dirIndex < direita.size()) {
            if (tipo == 0
                    ? esquerda.get(esqIndex).getNome().compareTo(direita.get(dirIndex).getNome()) <= 0
                    : esquerda.get(esqIndex).getNome().compareTo(direita.get(dirIndex).getNome()) > 0) {
                //if ((left.get(leftIndex).getNome().compareTo(right.get(rightIndex).getNome())) < 0) {
                arrayProduto.set(arrayIndex, esquerda.get(esqIndex));
                esqIndex++;
            } else {
                arrayProduto.set(arrayIndex, direita.get(dirIndex));
                dirIndex++;
            }
            arrayIndex++;
        }

        ArrayList<Produto> rest;
        int restIndex;
        if (esqIndex >= esquerda.size()) {
            // The left ArrayList has been use up...
            rest = direita;
            restIndex = dirIndex;
        } else {
            // The right ArrayList has been used up...
            rest = esquerda;
            restIndex = esqIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i = restIndex; i < rest.size(); i++) {
            arrayProduto.set(arrayIndex, rest.get(i));
            arrayIndex++;
        }
    }

        /**
     * Método para ordenar os produtos em ordem crescente de categoria
     *
     * @param arrayProduto
     * @return
     */
    public ArrayList<Produto> mergeSortCat(ArrayList<Produto> arrayProduto, int tipo) {
        ArrayList<Produto> esquerda = new ArrayList<Produto>();
        ArrayList<Produto> direita = new ArrayList<Produto>();
        int center;

        if (arrayProduto.size() == 1) {
            return arrayProduto;
        } else {
            center = arrayProduto.size() / 2;
            // copy the left half of whole into the left.
            for (int i = 0; i < center; i++) {
                esquerda.add(arrayProduto.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i = center; i < arrayProduto.size(); i++) {
                direita.add(arrayProduto.get(i));
            }

            // Sort the left and right halves of the arraylist.
            esquerda = mergeSortCat(esquerda, tipo);
            direita = mergeSortCat(direita, tipo);

            // Merge the results back together.
            mergeCat(esquerda, direita, arrayProduto, tipo);
        }
        return arrayProduto;
    }

    private void mergeCat(ArrayList<Produto> esquerda, ArrayList<Produto> direita, ArrayList<Produto> arrayProduto, int tipo) {
        int esqIndex = 0;
        int dirIndex = 0;
        int arrayIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (esqIndex < esquerda.size() && dirIndex < direita.size()) {
            if (tipo == 0
                    ? esquerda.get(esqIndex).getCategoria().compareTo(direita.get(dirIndex).getCategoria()) <= 0
                    : esquerda.get(esqIndex).getCategoria().compareTo(direita.get(dirIndex).getCategoria()) > 0) {
            //if ((left.get(leftIndex).getCategoria().compareTo(right.get(rightIndex).getCategoria())) < 0) {
                arrayProduto.set(arrayIndex, esquerda.get(esqIndex));
                esqIndex++;
            } else {
                arrayProduto.set(arrayIndex, direita.get(dirIndex));
                dirIndex++;
            }
            arrayIndex++;
        }

        ArrayList<Produto> rest;
        int restIndex;
        if (esqIndex >= esquerda.size()) {
            // The left ArrayList has been use up...
            rest = direita;
            restIndex = dirIndex;
        } else {
            // The right ArrayList has been used up...
            rest = esquerda;
            restIndex = esqIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i = restIndex; i < rest.size(); i++) {
            arrayProduto.set(arrayIndex, rest.get(i));
            arrayIndex++;
        }
    }

        /**
     * Método para ordenar os produtos em ordem crescente de categoria
     *
     * @param arrayProduto
     * @return
     */
    public ArrayList<Produto> mergeSortPreco(ArrayList<Produto> arrayProduto, int tipo) {
        ArrayList<Produto> esquerda = new ArrayList<Produto>();
        ArrayList<Produto> direita = new ArrayList<Produto>();
        int center;

        if (arrayProduto.size() == 1) {
            return arrayProduto;
        } else {
            center = arrayProduto.size() / 2;
            // copy the left half of whole into the left.
            for (int i = 0; i < center; i++) {
                esquerda.add(arrayProduto.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i = center; i < arrayProduto.size(); i++) {
                direita.add(arrayProduto.get(i));
            }

            // Sort the left and right halves of the arraylist.
            esquerda = mergeSortPreco(esquerda, tipo);
            direita = mergeSortPreco(direita, tipo);

            // Merge the results back together.
            mergePreco(esquerda, direita, arrayProduto, tipo);
        }
        return arrayProduto;
    }

    private void mergePreco(ArrayList<Produto> esquerda, ArrayList<Produto> direita, ArrayList<Produto> arrayProduto, int tipo) {
        int esqIndex = 0;
        int dirIndex = 0;
        int arrayIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (esqIndex < esquerda.size() && dirIndex < direita.size()) {
            if (tipo == 0
                    ? Float.compare(esquerda.get(esqIndex).getPreco(),direita.get(dirIndex).getPreco()) <= 0
                    : Float.compare(esquerda.get(esqIndex).getPreco(),direita.get(dirIndex).getPreco()) > 0) {
                arrayProduto.set(arrayIndex, esquerda.get(esqIndex));
                esqIndex++;
            } else {
                arrayProduto.set(arrayIndex, direita.get(dirIndex));
                dirIndex++;
            }
            arrayIndex++;
        }

        ArrayList<Produto> rest;
        int restIndex;
        if (esqIndex >= esquerda.size()) {
            // The left ArrayList has been use up...
            rest = direita;
            restIndex = dirIndex;
        } else {
            // The right ArrayList has been used up...
            rest = esquerda;
            restIndex = esqIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i = restIndex; i < rest.size(); i++) {
            arrayProduto.set(arrayIndex, rest.get(i));
            arrayIndex++;
        }
    }

}
