import api.aps.domain.Produto;
import api.aps.requests.ProdutoPostRequestBody;
import api.aps.requests.ProdutoPutRequestBody;

public class ProdutoMapper {
    public static Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody) {
        Produto produto = new Produto();
        produto.setNome(produtoPostRequestBody.getNome());
        produto.setQtdDisponivel(produtoPostRequestBody.getQtdDisponivel());
        produto.setFornecedor(produtoPostRequestBody.getFornecedor());
        return produto;
    }

    public static Produto toProduto(ProdutoPutRequestBody produtoPutRequestBody) {
        Produto produto = new Produto();
        produto.setId(produtoPutRequestBody.getId());
        produto.setNome(produtoPutRequestBody.getNome());
        produto.setQtdDisponivel(produtoPutRequestBody.getQtdDisponivel());
        produto.setFornecedor(produtoPutRequestBody.getFornecedor());
        return produto;
    }
}
