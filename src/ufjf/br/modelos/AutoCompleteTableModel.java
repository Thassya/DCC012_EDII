/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.modelos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thassya
 */
public class AutoCompleteTableModel extends AbstractTableModel {

    private final int COL_NOME = 0;
    private final int COL_CATEGORIA = 1;
    private final int COL_PRECO = 2;

    ArrayList<Produto> listaProdutos;
 
    public AutoCompleteTableModel() {
        listaProdutos = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        try {
            return listaProdutos.size();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {        

        switch (columnIndex) {
            case COL_NOME:
                return listaProdutos.get(rowIndex).getNome();
            case COL_CATEGORIA:
                return listaProdutos.get(rowIndex).getCategoria();
            case COL_PRECO:
                return listaProdutos.get(rowIndex).getPreco();
            default:
                return "?";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case COL_NOME:
                return "Nome";
            case COL_CATEGORIA:
                return "Categoria";
            case COL_PRECO:
                return "Preço";
            default:
                return "?";
        }
    }
    
    
    public void preencheLista(ArrayList<Produto> lista){
        this.listaProdutos = lista;
        this.fireTableDataChanged();
    }
    
    public void constroiOrdenado(ArrayList<Produto> lista){
        this.listaProdutos = lista;
        this.fireTableDataChanged();
    }
}
