package api.aps.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "qtd_disponivel")
    private String qtdDisponivel;

    private String fornecedor;

    public Produto() {
    }

    public Produto(Long id, String nome, String qtdDisponivel, String fornecedor) {
        this.id = id;
        this.nome = nome;
        this.qtdDisponivel = qtdDisponivel;
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(String qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
}
