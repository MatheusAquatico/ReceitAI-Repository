package api.aps.controller;

import api.aps.domain.Receita;
import api.aps.requests.ReceitaPostRequestBody;
import api.aps.requests.ReceitaPutRequestBody;
import api.aps.service.ReceitaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/receitas")
@CrossOrigin(origins = "https://receitai-frontend.herokuapp.com")
public class ReceitaController {

    private static final Logger log = LoggerFactory.getLogger(ReceitaController.class);

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<Page<Receita>> list(Pageable pageable) throws InterruptedException {
        return ResponseEntity.ok(receitaService.listAll(pageable));
    }

    @GetMapping(path= "/getReceitas")
    public ResponseEntity<List<Receita>> getReceitas() throws InterruptedException {
        return ResponseEntity.ok(receitaService.getReceitas());
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<Receita> findById(@PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok(receitaService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody @Valid ReceitaPostRequestBody receitaPostRequestBody){
        return new ResponseEntity<>(receitaService.save(receitaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid ReceitaPutRequestBody receitaPutRequestBody) {
        receitaService.replace(receitaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
