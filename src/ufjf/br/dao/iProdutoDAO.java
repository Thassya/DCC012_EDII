package ufjf.br.dao;

import java.util.List;
import ufjf.br.modelos.Produto;

/**
 *
 * @author Thassya
 */
public interface iProdutoDAO {
    void Insere(Produto p) throws Exception;
    java.util.List<Produto> getTodos() throws Exception;
}
