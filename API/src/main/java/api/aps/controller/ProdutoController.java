package api.aps.controller;

import api.aps.domain.Produto;
import api.aps.requests.ProdutoPostRequestBody;
import api.aps.requests.ProdutoPutRequestBody;
import api.aps.service.ProdutoService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produtos")
@CrossOrigin(origins = "https://receitai-frontend.herokuapp.com")
public class ProdutoController {

    private static final Logger log = LogManager.getLogger(ProdutoController.class);
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> list(Pageable pageable) throws InterruptedException {
        return ResponseEntity.ok(produtoService.listAll(pageable));
    }

    @GetMapping(path= "/getProdutos")
    public ResponseEntity<List<Produto>> getProdutos() throws InterruptedException {
        return ResponseEntity.ok(produtoService.getProdutos());
    }

    @GetMapping(path= "/findByQtdDisponivel")
    public ResponseEntity<Page<Produto>> findByQtdDisponivel() throws InterruptedException {
        final Page<Produto> page = new PageImpl<>(produtoService.findByQtdDisponivel("0"));
        return ResponseEntity.ok(page);
    }

    @GetMapping(path= "/findFornecedorDistinctByQtdDisponivel")
    public ResponseEntity<Page<String>> findDistinctByQtdDisponivel() throws InterruptedException {
        final Page<String> page = new PageImpl<>(produtoService.findDistinctByQtdDisponivel("0"));
        return ResponseEntity.ok(page);
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok(produtoService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(produtoService.save(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid ProdutoPutRequestBody produtoPutRequestBody) {
        produtoService.replace(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
