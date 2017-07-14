package ufjf.br.dao;

import java.util.List;
import ufjf.br.modelos.Produto;

/**
 *
 * @author Thassya
 */
public interface iProdutoDAO {
    void insere(Produto p) throws Exception;
    List<Produto> getTodosProdutos() throws Exception;
    void lerArquivo();
}
