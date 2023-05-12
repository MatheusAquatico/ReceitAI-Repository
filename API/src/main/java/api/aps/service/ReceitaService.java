package api.aps.service;

import api.aps.domain.Receita;
import api.aps.exception.BadRequestException;
import api.aps.repository.ReceitaRepository;
import api.aps.requests.ReceitaPostRequestBody;
import api.aps.requests.ReceitaPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Page<Receita> listAll(Pageable pageable) {
        return receitaRepository.findAll(pageable);
    }

    public List<Receita> getReceitas() {
        return receitaRepository.findAll();
    }

    public Receita findByIdOrThrowBadRequestException(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Receita não encontrada"));
    }

    @Transactional
    public Receita save(ReceitaPostRequestBody receitaPostRequestBody) {
        Receita receita = new Receita();
        receita.setTitulo(receitaPostRequestBody.getTitulo());
        receita.setData(receitaPostRequestBody.getData());
        return receitaRepository.save(receita);
    }

    @Transactional
    public void delete(Long id) {
        receitaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ReceitaPutRequestBody receitaPutRequestBody) {
        Receita receitaSaved = findByIdOrThrowBadRequestException(receitaPutRequestBody.getId());
        Receita receita = new Receita();
        receita.setId(receitaSaved.getId());
        receita.setTitulo(receitaPutRequestBody.getTitulo());
        receita.setData(receitaPutRequestBody.getData());
        receitaRepository.save(receita);
    }
}
