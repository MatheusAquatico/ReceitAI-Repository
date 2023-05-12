package api.aps.repository;

import api.aps.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto,Long>, JpaSpecificationExecutor<Produto> {

    @Query("SELECT p FROM Produto p WHERE p.qtdDisponivel = :qtdDisponivel")
    List<Produto> findByQtdDisponivel(@Param("qtdDisponivel") String qtdDisponivel);

    @Query("SELECT p.fornecedor FROM Produto p WHERE p.qtdDisponivel = :qtdDisponivel GROUP BY p.fornecedor" )
    List<String> findDistinctByQtdDisponivel(@Param("qtdDisponivel") String qtdDisponivel);






}
