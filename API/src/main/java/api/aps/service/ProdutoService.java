package api.aps.service;

import api.aps.domain.Produto;
import api.aps.exception.BadRequestException;
import api.aps.repository.ProdutoRepository;
import api.aps.requests.ProdutoPostRequestBody;
import api.aps.requests.ProdutoPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> listAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> findByQtdDisponivel(String qtdDisponivel) {
        return produtoRepository.findByQtdDisponivel(qtdDisponivel);
    }

    public List<String> findDistinctByQtdDisponivel(String qtdDisponivel) {
        return produtoRepository.findDistinctByQtdDisponivel(qtdDisponivel);
    }

    public Produto findByIdOrThrowBadRequestException(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Produto não encontrado"));
    }

    @Transactional
    public Produto save(ProdutoPostRequestBody produtoPostRequestBody) {
        Produto produto = new Produto();
        produto.setNome(produtoPostRequestBody.getNome());
        produto.setQtdDisponivel(produtoPostRequestBody.getQtdDisponivel());
        produto.setFornecedor(produtoPostRequestBody.getFornecedor());
        return produtoRepository.save(produto);
    }

    @Transactional
    public void delete(Long id) {
        produtoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProdutoPutRequestBody produtoPutRequestBody) {
        Produto produtoSaved = findByIdOrThrowBadRequestException(produtoPutRequestBody.getId());
        Produto produto = new Produto();
        produto.setId(produtoSaved.getId());
        produto.setNome(produtoPutRequestBody.getNome());
        produto.setQtdDisponivel(produtoPutRequestBody.getQtdDisponivel());
        produto.setFornecedor(produtoPutRequestBody.getFornecedor());
        produtoRepository.save(produto);
    }
}
