package api.aps.repository;

import api.aps.domain.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReceitaRepository extends JpaRepository<Receita,Long>, JpaSpecificationExecutor<Receita> {
}
