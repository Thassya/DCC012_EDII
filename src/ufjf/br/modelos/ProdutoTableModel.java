/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.modelos;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import ufjf.br.dao.ProdutoDAO;
import ufjf.br.dao.iProdutoDAO;

/**
 *
 * @author Thassya
 */
public class ProdutoTableModel extends AbstractTableModel {

    private final int COL_NOME = 0;
    private final int COL_CATEGORIA = 1;
    private final int COL_PRECO = 2;
    private final int COL_DESCRICAO = 3;

    private TrieTernaria trie;
    private final iProdutoDAO dao;

    public ProdutoTableModel() {
        dao = new ProdutoDAO();
    }

    private void atualizaDados() {
        try {
            dao.getTodos();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ProdutoDAO getDao() {
        return (ProdutoDAO) dao;
    }

    @Override
    public int getRowCount() {
        atualizaDados();
        try {
            return dao.getTodos().size();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Produto> produtoList = new ArrayList<>();
        try {
            produtoList = dao.getTodos();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        switch (columnIndex) {
            case COL_NOME:
                return produtoList.get(rowIndex).getNome();
            case COL_CATEGORIA:
                return produtoList.get(rowIndex).getCategoria();
            case COL_PRECO:
                return produtoList.get(rowIndex).getPreco();
            case COL_DESCRICAO:
                return produtoList.get(rowIndex).getDescricao();
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
            case COL_DESCRICAO:
                return "Descrição";
            default:
                return "?";
        }
    }
}
