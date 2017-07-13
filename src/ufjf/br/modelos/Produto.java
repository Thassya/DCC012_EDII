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
    private float Preco;

    public Produto(){

    }

    public Produto(String Nome, String Categoria, String Descricao, float Preco) {
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

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    
}
