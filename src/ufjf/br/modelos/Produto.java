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
public class Produto {
    private String Nome;
    private String Categoria;
    private String Descricao;
    private String Preco;

    public Produto(){

    }

    public Produto(String Nome, String Categoria, String Descricao, String Preco) {
        this.Nome = Nome;
        this.Categoria = Categoria;
        this.Descricao = Descricao;
        this.Preco = Preco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String Preco) {
        this.Preco = Preco;
    }

    
}
