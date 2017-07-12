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

    public void mergesort(Produto[] vetor, int inicio, int fim) {
        int meio, i;
        if (inicio < fim - 1) {
            meio = (inicio + fim) / 2;
            mergesort(vetor, inicio, meio);
            mergesort(vetor, meio, fim);
            intercala(vetor, inicio, meio, fim);
        }
    }

    public void intercala(Produto[] vetor, int inicioL1, int inicioL2, int fim) {
        int ind_inicio, ind_inicioL2, ind_aux;
        Produto[] vetor_aux = new Produto[fim-inicioL1];
        ind_inicio = inicioL1;
        ind_inicioL2 = inicioL2;
        ind_aux = 0;

        while ((ind_inicio < inicioL2) && (ind_inicioL2 < fim)) {

//            if (vetor[ind_inicio].getNome() <= vetor[ind_inicioL2].getNome()) { //<=
//                vetor_aux[ind_aux] = vetor[ind_inicio];
//                ind_inicio++;
//            } else {
//                vetor_aux[ind_aux] = vetor[ind_inicioL2];
//                ind_inicioL2++;
//            }

            ind_aux++;
        }
        while (ind_inicio < inicioL2) {
            vetor_aux[ind_aux] = vetor[ind_inicio];
            ind_aux++;
            ind_inicio++;
        }

        while (ind_inicioL2 < fim) {
            vetor_aux[ind_aux] = vetor[ind_inicioL2];
            ind_aux++;
            ind_inicioL2++;
        }

        for (ind_inicio = inicioL1; ind_inicio < fim; ind_inicio++) {
            vetor[ind_inicio] = vetor_aux[ind_inicio - inicioL1];
        }
    }
}